package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoDto;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.TodoRepository;
import org.terror.calendarapphard.util.UtilFind;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UtilFind utilFind;
    private final CalendarRepository calendarRepository;

    @Transactional
    public BaseResponseDto createTodo(Long memberId, RequestTodoDto reqTodo) {
        Member member = utilFind.memberFindById(memberId);
        Todo todo = new Todo(reqTodo);
        todo.setMember(member); // 멤버 연관관계 맺어줌, 작성자를 알아야해서
        Calendar calendar = Calendar.builder()
                .member(member) // 멤버 연관관계 세팅
                .todo(todo) // 일정 연관관계 세팅
                .build();
        //멤버는 이미 저장되있으니 딱히 저장할 필요 없음
        todoRepository.save(todo);
        calendarRepository.save(calendar);
        return new BaseResponseDto(BaseResponseEnum.TODO_SAVE_SUCCESS);
    }

    @Transactional(readOnly = true)
    public ResponseTodoDto getTodo(Long id) {
        Todo todo = utilFind.todoFindById(id);
        return new ResponseTodoDto(todo);
    }

    @Transactional
    public BaseResponseDto updateTodo(Long id, RequestTodoDto reqDto) {
        Todo todo = utilFind.todoFindById(id);
        //트랜잭션 환경내에 있는 더티체킹으로인하여 업데이트됨
        todo.setTitle(reqDto.getTitle());
        todo.setDetail(reqDto.getDetail());
        return new BaseResponseDto(BaseResponseEnum.TODO_UPDATE_SUCCESS);
    }

    @Transactional
    public BaseResponseDto deleteTodo(Long id) {
        Todo todo = utilFind.todoFindById(id);
        todoRepository.delete(todo);
        return new BaseResponseDto(BaseResponseEnum.TODO_DELETE_SUCCESS);
    }
}
