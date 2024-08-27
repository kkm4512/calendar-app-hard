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
    @GetMapping("/{todoId}/{id}")
    public ResponseCommentDto getComment(@PathVariable Long todoId, @PathVariable Long id){
        return commentService.getComment(todoId,id);
    }

    // 댓글 다건 조회
    @GetMapping("/{todoId}")
    public List<ResponseCommentDto> getAllComments(@PathVariable Long todoId){
        return commentService.getAllComments(todoId);
    }

    // 댓글 수정
    @PutMapping("/{todoId}/{id}")
    public BaseResponseDto updateComment(@PathVariable Long todoId, @PathVariable Long id, @RequestBody RequestCommentDto reqDto){
        return commentService.updateComment(todoId,id,reqDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{todoId}/{id}")
    public BaseResponseDto deleteComment(@PathVariable Long todoId, @PathVariable Long id){
        return commentService.deleteComment(todoId,id);
    }
}
