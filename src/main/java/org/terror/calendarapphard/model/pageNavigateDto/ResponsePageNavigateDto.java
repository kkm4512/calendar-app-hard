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
    private Long id;
    private String title;
    private String author;
    private String detail;
    private int commentCount;

    public ResponsePageNavigateDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.detail = todo.getDetail();
        this.author = todo.getMember().getAuthor();
        this.setCreatedAt(todo.getCreatedAt());
        this.setUpdatedAt(todo.getUpdatedAt());
        this.commentCount = todo.getCommnetList().size();
    }
}
