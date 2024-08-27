package org.terror.calendarapphard.init;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.commentDto.RequestCommentDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.repository.TodoRepository;

@SpringBootTest
public class TestInfoInit {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("테스트용 데이터 넣기")
    @Rollback(value = false)
    void 테스트_데이터_넣기(){
        for ( int i=0; i<100; i++ ) {
            RequestTodoDto reqDto = new RequestTodoDto("author" + i,"title" + i, "detail" + i);
            Todo todo = new Todo(reqDto);
            todoRepository.save(todo);
            for ( int j=0; j<10; j++ ) {
                RequestCommentDto requestCommentDto = new RequestCommentDto("author" + j, "detail" + j);
                Comment comment = new Comment(requestCommentDto);
                comment.setTodo(todo);
                commentRepository.save(comment);
            }
        }
    }
}
