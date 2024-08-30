package org.terror.calendarapphard.model.commentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 클라이언트의 댓글 저장,수정 요청을 처리하기위한 DTO 클래스
@Getter
@NoArgsConstructor
public class RequestCommentDto {
    @NotBlank(message = "내용은 공백 일 수 없습니다")
    private String detail;
}
