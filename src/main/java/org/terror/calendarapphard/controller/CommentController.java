package org.terror.calendarapphard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.commentDto.RequestCommentDto;
import org.terror.calendarapphard.model.commentDto.ResponseCommentDto;
import org.terror.calendarapphard.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    //댓글 저장
    @PostMapping("/{todoId}")
    public BaseResponseDto createComment(@PathVariable Long todoId, @RequestBody RequestCommentDto reqDto){
        return commentService.createComment(todoId,reqDto);
    }

    // 댓글 단건 조회
    @GetMapping("/{id}")
    public ResponseCommentDto getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    // 댓글 다건 조회
    // 어떤 일정에대한 댓글 다건조회를 해야해서 todoId 받아옴
    // 다건 조회할때 조건을 추가할수있게 Param 로 받게함
    @GetMapping("/query")
    public List<ResponseCommentDto> getAllComments(@RequestParam Long todoId){
        return commentService.getAllComments(todoId);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public BaseResponseDto updateComment(@PathVariable Long id, @RequestBody RequestCommentDto reqDto){
        return commentService.updateComment(id,reqDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public BaseResponseDto deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
