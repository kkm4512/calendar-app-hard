package org.terror.calendarapphard.model.commentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.model.TimeStampDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseCommentDto extends TimeStampDto {
    private Long id;
    private String author;
    private String detail;

    //Entity -> Dto
    public ResponseCommentDto(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor();
        this.detail = comment.getDetail();
        this.setCreatedAt(comment.getCreatedAt());
        this.setUpdatedAt(comment.getUpdatedAt());
    }
}
