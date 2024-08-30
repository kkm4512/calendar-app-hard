package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.commentDto.RequestCommentDto;
import org.terror.calendarapphard.model.commentDto.ResponseCommentDto;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.util.UtilFind;
import org.terror.calendarapphard.util.UtilResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UtilFind utilFind;

    /**
     * 댓글 저장 비즈니스 로직
     *
     * @param todoId / 댓글을 저장할 게시글의 ID
     * @throws HandleNotFoundException / 댓글이 존재 하지않을 경우 발생
     * @param reqDto / 저장할 댓글의 내용
     * @return / 댓글 저장 성공시 ResponseEntity<BaseResponseDto> 반환
     *
     * 댓글을 특정 게시글에 저장하고, 저장 성공 여부를 알리는 응답 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> createComment(Long todoId, RequestCommentDto reqDto) {
        Todo todo = utilFind.todoFindById(todoId);
        Comment comment = Comment.builder()
                .todo(todo)
                .detail(reqDto.getDetail())
                .build();
        commentRepository.save(comment);
        return UtilResponse.getResponseEntity(BaseResponseEnum.COMMENT_SAVE_SUCCESS);
    }


    /**
     * 단건 댓글 조회 비즈니스 로직
     *
     * @param id  / 조회할 댓글 ID
     * @throws HandleNotFoundException / 댓글이 존재 하지않을 경우 발생
     * @return / 조회 성공시 댓글 반환
     *
     * 댓글 ID를 기반으로 조회하고, 성공할시 조회된 댓글 반환합니다
     */
    @Transactional(readOnly = true)
    public ResponseCommentDto getComment(Long id) {
        Comment comment = utilFind.commentFindById(id);
        return new ResponseCommentDto(comment);
    }

    /**
     * 다건 댓글 조회 비즈니스 로직
     *
     * @return  List<ResponseCommentDto> / 댓글이 존재할경우 모든 댓글 반환, 없다면 빈 리스트 반환
     *
     * 댓귿들이 존재하면 모든댓글을 반환하고, 없다면 빈 리스트를 반환합니다
     */
    @Transactional(readOnly = true)
    public List<ResponseCommentDto> getAllComments() {
        List<Comment> commentList = utilFind.commentsFindAll();
        return commentList.stream().map(ResponseCommentDto::new).toList();
    }


    /**
     * 댓글 수정 비즈니스 로직
     *
     * @param id / 수정할 댓글의 ID
     * @throws HandleNotFoundException / 댓글이 존재 하지않을 경우 발생
     * @param reqDto / 수정할 댓글의 내용
     * @return ResponseEntity<BaseResponseDto> / 수정 성공 여부를 알리는 응답 반환
     *
     * 특정 댓글을 수정하고, 성공 여부를 알리는 응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> updateComment(Long id, RequestCommentDto reqDto) {
        Comment comment = utilFind.commentFindById(id);
        Comment updateComment = Comment.builder()
                .id(comment.getId())
                .todo(comment.getTodo())
                .detail(reqDto.getDetail())
                .build();
        commentRepository.save(updateComment);
        return UtilResponse.getResponseEntity(BaseResponseEnum.COMMENT_UPDATE_SUCCESS);
    }

    /**
     * 댓글 삭제 비즈니스 로직
     *
     * @param id / 삭제할 댓글의 ID
     * @throws HandleNotFoundException / 댓글이 존재 하지않을 경우 발생
     * @return ResponseEntity<BaseResponseDto> / 삭제 성공 여부를 알리는 응답 반환
     *
     * 특정 댓글을 삭제하고, 성공 여부를 알리는 응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> deleteComment(Long id) {
        Comment comment = utilFind.commentFindById(id);
        commentRepository.delete(comment);
        return UtilResponse.getResponseEntity(BaseResponseEnum.COMMENT_DELETE_SUCCESS);
    }
}
