package org.terror.calendarapphard.model.TodoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.TimeStampDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseTodoDto extends TimeStampDto {
    private Long id;
    private String title;
    private String author;
    private String detail;

    // Entity -> Dto
    public ResponseTodoDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.author = todo.getAuthor();
        this.detail = todo.getDetail();
        this.setCreatedAt(todo.getCreatedAt());
        this.setUpdatedAt(todo.getUpdatedAt());
    }
}
