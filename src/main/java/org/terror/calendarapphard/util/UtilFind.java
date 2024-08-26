package org.terror.calendarapphard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.repository.TodoRepository;

@RequiredArgsConstructor
@Component
public class UtilFind {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    // todoId로 todo 찾는 메서드
    public Todo todoFindById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.USER_NOT_FOUND));
    }

    //todoId,commentId로 comment 찾는 메서드
    public Comment commentFindById(Long todoId, Long commentId){
        return commentRepository.findByTodoIdAndId(todoId,commentId).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.COMMENT_NOT_FOUND));
    }
}
