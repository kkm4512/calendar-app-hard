package org.terror.calendarapphard.model.todoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.TimeStampDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseTodoDto extends TimeStampDto {
    private Long id;
    private String title;
    private String author;
    private String detail;
    private String email;
    private List<Comment> commnetList = new ArrayList<>();


    // Entity -> Dto
    public ResponseTodoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.author = todo.getMember().getAuthor();
        this.email = todo.getMember().getEmail();
        this.detail = todo.getDetail();
        // 페이지네비게이션 에서 댓클 갯수 새주는거 때문에 넣어둠
        this.commnetList.addAll(todo.getCommnetList());
        this.setCreatedAt(todo.getCreatedAt());
        this.setUpdatedAt(todo.getUpdatedAt());
    }
}
