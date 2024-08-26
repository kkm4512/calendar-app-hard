package org.terror.calendarapphard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleUserNotFoundException;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.repository.TodoRepository;

@RequiredArgsConstructor
@Component
public class UtilFind {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public Todo todoFindById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new HandleUserNotFoundException(BaseResponseEnum.USER_NOT_FOUND));
    }

}
