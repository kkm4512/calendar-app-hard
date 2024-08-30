package org.terror.calendarapphard.model.todoDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 클라이언트의 일정 저장,수정 요청을 처리하기위한 DTO 클래스
@Getter
@AllArgsConstructor
public class RequestTodoDto {
    @NotBlank(message = "제목은 공백 일 수 없습니다")
    private final String title;
    @NotBlank(message = "내용은 공백 일 수 없습니다")
    private final String detail;
}
