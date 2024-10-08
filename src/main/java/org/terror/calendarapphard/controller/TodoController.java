package org.terror.calendarapphard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponseDto> createTodo(@PathVariable Long memberId , @RequestBody @Valid RequestTodoDto reqTodo, HttpServletRequest req){
        return todoService.createTodo(memberId,reqTodo, req);
    }
    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseTodoDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    // 일정 전체 조회
    @GetMapping
    public List<ResponseTodoFindAllDto> getAllTodo(){
        return todoService.getAllTodo();
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateTodo(@PathVariable Long id, @RequestBody @Valid RequestTodoDto reqDto){
        return todoService.updateTodo(id,reqDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteTodo(@PathVariable Long id){
        return todoService.deleteTodo(id);
    }
}
