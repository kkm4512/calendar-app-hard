package org.terror.calendarapphard.model.todoDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTodoDto {
    @NotBlank(message = "제목은 공백 일 수 없습니다")
    private String title;
    @NotBlank(message = "내용은 공백 일 수 없습니다")
    private String detail;
}
