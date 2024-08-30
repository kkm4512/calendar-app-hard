package org.terror.calendarapphard.model.commentDto;

import lombok.Getter;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.model.TimeStampDto;

// 서버에서 처리된 댓글을 클라이언트로 보내기위한 DTO 클래스
@Getter
public class ResponseCommentDto extends TimeStampDto {
    private final Long id;
    private final String author;
    private final String detail;

    //Entity -> Dto
    public ResponseCommentDto(Comment comment) {
        super(comment.getCreatedAt(),comment.getUpdatedAt());
        this.id = comment.getId();
        this.author = comment.getTodo().getMember().getAuthor();
        this.detail = comment.getDetail();
    }
}
