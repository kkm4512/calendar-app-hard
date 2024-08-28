package org.terror.calendarapphard.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoFindAllDto;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.TodoRepository;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilDate;
import org.terror.calendarapphard.util.UtilFind;
import org.terror.calendarapphard.enums.UriEnum;

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

    @Transactional
    public BaseResponseDto createTodo(Long memberId, RequestTodoDto reqTodo, HttpServletRequest req) {
        String todayWeather = getWeatherApi(req);
        Member member = utilFind.memberFindById(memberId);
        Todo todo = new Todo(reqTodo);
        todo.setWeather(todayWeather);
        todo.setMember(member); // 멤버 연관관계 맺어줌, 작성자를 알아야해서
        Calendar calendar = Calendar.builder()
                .member(member) // 멤버 연관관계 세팅
                .todo(todo) // 일정 연관관계 세팅
                .build();
        //멤버는 이미 저장되있으니 딱히 저장할 필요 없음
        todoRepository.save(todo);
        calendarRepository.save(calendar);
        System.out.println(todo.getCreatedAt());
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

    public List<ResponseTodoFindAllDto> getAllTodo() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(ResponseTodoFindAllDto::new).toList();
    }

    // 내가만든 WeatherAPI 로 요청 보냄
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
