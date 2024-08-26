package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.TodoDto.RequestTodoDto;
import org.terror.calendarapphard.model.TodoDto.ResponseTodoDto;
import org.terror.calendarapphard.repository.TodoRepository;
import org.terror.calendarapphard.util.UtilFind;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UtilFind utilFind;

    @Transactional
    public BaseResponseDto createTodo(RequestTodoDto reqTodo) {
        Todo todo = new Todo(reqTodo);
        todoRepository.save(todo);
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
        todo.setAuthor(reqDto.getAuthor());
        todo.setDetail(reqDto.getDetail());
        return new BaseResponseDto(BaseResponseEnum.TODO_UPDATE_SUCCESS);
    }

}
