package org.terror.calendarapphard.model.commentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestCommentDto {
    private String author;
    private String detail;
}
