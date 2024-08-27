package org.terror.calendarapphard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.model.todoDto.ResponseTodoDto;
import org.terror.calendarapphard.service.TodoService;

@RestController
@RequestMapping("/api/calendars")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 일정 저장
    @PostMapping
    public BaseResponseDto createTodo(@RequestBody RequestTodoDto reqTodo){
        return todoService.createTodo(reqTodo);
    }
    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseTodoDto getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public BaseResponseDto updateTodo(@PathVariable Long id, @RequestBody RequestTodoDto reqDto){
        return todoService.updateTodo(id,reqDto);
    }
}
