package org.terror.calendarapphard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponseDto> createComment(@PathVariable Long todoId, @RequestBody @Valid RequestCommentDto reqDto){
        return commentService.createComment(todoId,reqDto);
    }

    // 댓글 단건 조회
    @GetMapping("/{id}")
    public ResponseCommentDto getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    // 댓글 다건 조회
    @GetMapping
    public List<ResponseCommentDto> getAllComments(){
        return commentService.getAllComments();
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateComment(@PathVariable Long id, @RequestBody @Valid RequestCommentDto reqDto){
        return commentService.updateComment(id,reqDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
