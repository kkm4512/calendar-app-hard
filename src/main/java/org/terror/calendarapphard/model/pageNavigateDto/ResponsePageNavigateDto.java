package org.terror.calendarapphard.model.pageNavigateDto;

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
public class ResponsePageNavigateDto extends TimeStampDto {
    private String title;
    private String author;
    private String detail;
    private int commentCount;

    public ResponsePageNavigateDto(Todo todo) {
        this.title = todo.getTitle();
        this.author = todo.getAuthor();
        this.detail = todo.getDetail();
        this.setCreatedAt(todo.getCreatedAt());
        this.setUpdatedAt(todo.getUpdatedAt());
        this.commentCount = todo.getCommnetList().size();
    }
}
