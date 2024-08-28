package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.commentDto.RequestCommentDto;
import org.terror.calendarapphard.model.commentDto.ResponseCommentDto;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.util.UtilFind;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UtilFind utilFind;


    @Transactional
    public BaseResponseDto createComment(Long todoId,RequestCommentDto reqDto) {
        Todo todo = utilFind.todoFindById(todoId);
        Comment comment = new Comment(reqDto);
        comment.setTodo(todo);
        commentRepository.save(comment);
        return new BaseResponseDto(BaseResponseEnum.COMMENT_SAVE_SUCCESS);
    }


    @Transactional(readOnly = true)
    public ResponseCommentDto getComment(Long id) {
        Comment comment = utilFind.commentFindById(id);
        return new ResponseCommentDto(comment);
    }


    @Transactional(readOnly = true)
    public List<ResponseCommentDto> getAllComments(Long todoId) {
        //이부분은 일부러 옵셔널로 받지않음
        //댓글은 비어있을 수도 있기때문에, 만약 비어있다면 그냥 빈 리스트를 반환시켜주기 위해서
        List<Comment> commentList = commentRepository.findByTodoId(todoId);
        return commentList.stream().map(ResponseCommentDto::new).toList();
    }


    @Transactional
    public BaseResponseDto updateComment(Long id, RequestCommentDto reqDto) {
        Comment comment = utilFind.commentFindById(id);
        comment.setDetail(reqDto.getDetail());
        return new BaseResponseDto(BaseResponseEnum.COMMENT_UPDATE_SUCCESS);
    }

    @Transactional
    public BaseResponseDto deleteComment(Long id) {
        Comment comment = utilFind.commentFindById(id);
        commentRepository.delete(comment);
        return new BaseResponseDto(BaseResponseEnum.COMMENT_DELETE_SUCCESS);
    }
}
