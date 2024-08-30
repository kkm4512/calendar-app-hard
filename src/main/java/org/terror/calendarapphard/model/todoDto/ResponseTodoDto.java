package org.terror.calendarapphard.model.todoDto;

import lombok.Getter;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.TimeStampDto;

import java.util.ArrayList;
import java.util.List;

// 서버에서 처리된 일정을 클라이언트로 보내기위한 DTO 클래스
@Getter
public class ResponseTodoDto extends TimeStampDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String detail;
    private final String email;
    private final List<Comment> commnetList = new ArrayList<>();


    // Entity -> Dto
    public ResponseTodoDto(Todo todo) {
        super(todo.getCreatedAt(),todo.getUpdatedAt());
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.author = todo.getMember().getAuthor();
        this.email = todo.getMember().getEmail();
        this.detail = todo.getDetail();
        // 페이지네비게이션 에서 댓클 갯수 새주는거 때문에 넣어둠
        this.commnetList.addAll(todo.getCommnetList());
    }
}
