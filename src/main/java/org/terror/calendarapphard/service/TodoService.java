package org.terror.calendarapphard.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.enums.UriEnum;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoFindAllDto;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.TodoRepository;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilDate;
import org.terror.calendarapphard.util.UtilFind;
import org.terror.calendarapphard.util.UtilResponse;

import java.net.URI;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UtilFind utilFind;
    private final CalendarRepository calendarRepository;
    private final RestTemplate restTemplate;
    private final UtilDate utilDate;
    private final JwtManager jm;

    public TodoService(
            TodoRepository todoRepository,
            UtilFind utilFind,
            CalendarRepository calendarRepository,
            RestTemplateBuilder builder,
            UtilDate utilDate,
            JwtManager jm
    ) {
        this.todoRepository = todoRepository;
        this.utilFind = utilFind;
        this.calendarRepository = calendarRepository;
        this.restTemplate = builder.build();
        this.utilDate = utilDate;
        this.jm = jm;
    }

    /**
     * 일정 저장 비즈니스 로직
     * @param memberId / 일정을 저장시킬, 유저의 ID
     * @throws HandleNotFoundException / 유저가 존재하지 않을시 발생
     * @param reqTodo / 저장할 일정의 정보
     * @param req / 헤더에 있는 Jwt값 가져오기 위함
     * @return ResponseEntity<BaseResponseDto> / 일정 저장 성공시, 성공 응답 반환
     *
     * 날씨를 조회하는 API 메서드를 호출하여 저장한후, 일정을 저장합니다
     * 일정이 저장될때 캘린더에도 연관관계를 맺어주고 저장합니다
     * 성공적으로 저장되면, 성공 응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> createTodo(Long memberId, RequestTodoDto reqTodo, HttpServletRequest req) {
        String weather = getWeatherApi(req);
        Member member = utilFind.memberFindById(memberId);
        Todo todo = Todo.builder()
                .title(reqTodo.getTitle())
                .detail(reqTodo.getDetail())
                .member(member)
                .weather(weather)
                .build();

        Calendar calendar = Calendar.builder()
                .member(member) // 멤버 연관관계 세팅
                .todo(todo) // 일정 연관관계 세팅
                .build();
        //멤버는 이미 저장되있으니 딱히 저장할 필요 없음
        calendarRepository.save(calendar);
        todoRepository.save(todo);
        return UtilResponse.getResponseEntity(BaseResponseEnum.TODO_SAVE_SUCCESS);
    }

    /**
     * 단건 일정 조회 비즈니스 로직
     *
     * @param id / 조회할 일정 ID
     * @throws HandleNotFoundException / 일정이 존재하지 않을시 발생
     * @return ResponseTodoDto / 조회 성공시, 조회된 일정 반환
     *
     * 성공적으로 조회되면, 성공응답을 반환합니다
     */
    @Transactional(readOnly = true)
    public ResponseTodoDto getTodo(Long id) {
        Todo todo = utilFind.todoFindById(id);
        return new ResponseTodoDto(todo);
    }

    /**
     * 일정 수정 비즈니스 로직
     *
     * @param id / 수정할 일정 ID
     * @throws HandleNotFoundException / 일정이 존재하지 않을시 발생
     * @param reqDto / 수정할 일정 내용
     * @return ResponseEntity<BaseResponseDto> / 수정에 성공할시, 성공 응답 반환
     *
     * 성공적으로 수정하면, 성공응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> updateTodo(Long id, RequestTodoDto reqDto) {
        Todo todo = utilFind.todoFindById(id);
        Todo updateTodo = Todo.builder()
                .id(todo.getId())
                .title(reqDto.getTitle())
                .detail(reqDto.getDetail())
                .weather(todo.getWeather())
                .member(todo.getMember())
                .build();
        todoRepository.save(updateTodo);
        return UtilResponse.getResponseEntity(BaseResponseEnum.TODO_UPDATE_SUCCESS);
    }

    /**
     * 일정 삭제 비즈니스 로직
     *
     * @param id / 삭제할 일정 ID
     * @throws HandleNotFoundException / 일정이 존재하지 않을시 발생
     * @return ResponseEntity<BaseResponseDto> / 삭제에 성공하면, 성공응답 반환
     *
     * 삭제에 성공하면, 성공응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> deleteTodo(Long id) {
        Todo todo = utilFind.todoFindById(id);
        todoRepository.delete(todo);
        return UtilResponse.getResponseEntity(BaseResponseEnum.TODO_DELETE_SUCCESS);
    }

    /**
     * 다건 일정 조회 비즈니스 로직
     *
     * @return List<ResponseTodoFindAllDto> / 일정들이 존재하면 모든 일정 반환, 없다면 빈 리스트 반환
     *
     * 일정들을 조회하였을떄, 일정들이 있다면 일정들을 반환하고 없다면 빈리스트를 반환합니다
     */
    @Transactional(readOnly = true)
    public List<ResponseTodoFindAllDto> getAllTodo() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(ResponseTodoFindAllDto::new).toList();
    }

    /**
     * 날씨 조회 비즈니스 로직
     *
     * @param req / 헤더에 있는 Jwt값 받아오기 위함
     * @return String / 조회에 성공하면 오늘 날씨 반환
     *
     * 오늘 일자의 날씨를 반환합니다
     */
    private String getWeatherApi(HttpServletRequest req){
        String jwt = jm.getJwtFromHeaderOrigin(req);
        String today = utilDate.getTodayFormat();
        URI uri = UriComponentsBuilder
                .fromUriString(UriEnum.LOCALHOST.getUri())
                .path(UriEnum.WEATHER_GET_API_PATH.getUri())
                .queryParam("today",today)
                .build()
                .toUri();
        RequestEntity<Void> reqEntity = RequestEntity
                .get(uri)
                // Header 에 Jwt 넣어주기
                .header(JwtManager.AUTHORIZATION_HEADER, jwt)
                .build();
        return restTemplate.exchange(reqEntity, String.class).getBody();
    }
}
