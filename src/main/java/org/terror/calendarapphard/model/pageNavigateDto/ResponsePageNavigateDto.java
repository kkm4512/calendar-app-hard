package org.terror.calendarapphard.model.pageNavigateDto;

import lombok.Getter;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.TimeStampDto;

// 서버에서 처리된 페이지를 클라이언트로 보내기위한 DTO 클래스
@Getter
public class ResponsePageNavigateDto extends TimeStampDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String detail;
    private final int commentCount;

    public ResponsePageNavigateDto(Todo todo) {
        super(todo.getCreatedAt(),todo.getUpdatedAt());
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.detail = todo.getDetail();
        this.author = todo.getMember().getAuthor();
        this.commentCount = todo.getCommnetList().size();
    }
}
