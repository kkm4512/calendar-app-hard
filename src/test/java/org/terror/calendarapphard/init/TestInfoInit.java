package org.terror.calendarapphard.init;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.terror.calendarapphard.model.commentDto.RequestCommentDto;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;
import org.terror.calendarapphard.service.CommentService;
import org.terror.calendarapphard.service.MemberService;
import org.terror.calendarapphard.service.TodoService;

@SpringBootTest
public class TestInfoInit {
    @Autowired
    MemberService memberService;
    @Autowired
    TodoService todoService;
    @Autowired
    CommentService commentService;


    @Test
    @DisplayName("테스트용 데이터 넣기")
    @Rollback(value = false)
    void 테스트_데이터_넣기() {
        for (int x = 0; x < 5; x++) {
            RequestMemberDto reqMemberDto = new RequestMemberDto("author" + x, "test@naver.com"+ x,"!@skdud340");
            memberService.createMember(reqMemberDto);
            for (int i = 0; i < 6; i++) {
                RequestTodoDto reqTodoDto = new RequestTodoDto("title" + i, "detail" + i);
                todoService.createTodo(x+1L,reqTodoDto);
                for (int j = 0; j < 7; j++) {
                    RequestCommentDto reqCommentDto = new RequestCommentDto("detail" + j);
                    commentService.createComment(x+1L,reqCommentDto);
                }
            }
        }
    }
}
