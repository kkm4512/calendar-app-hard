package org.terror.calendarapphard.model.commentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentDto {
    @NotBlank(message = "내용은 공백 일 수 없습니다")
    private String detail;
}
