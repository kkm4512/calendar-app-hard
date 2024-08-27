package org.terror.calendarapphard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoFindAllDto;
import org.terror.calendarapphard.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 일정 저장
    @PostMapping("/{memberId}")
    public BaseResponseDto createTodo(@PathVariable Long memberId , @RequestBody RequestTodoDto reqTodo){
        return todoService.createTodo(memberId,reqTodo);
    }
    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseTodoDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    // 일정 전체 조회
    // 여기서 RequestParam 으로 받아서
    // 요청에따라 담당 유저 정보를 보여줄지, 안보여줄지 하게하자
    @GetMapping
    public List<ResponseTodoFindAllDto> getAllTodo(){
        return todoService.getAllTodo();
    }

    // 일정 수정
    @PutMapping("/{id}")
    public BaseResponseDto updateTodo(@PathVariable Long id, @RequestBody RequestTodoDto reqDto){
        return todoService.updateTodo(id,reqDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public BaseResponseDto deleteTodo(@PathVariable Long id){
        return todoService.deleteTodo(id);
    }
}
