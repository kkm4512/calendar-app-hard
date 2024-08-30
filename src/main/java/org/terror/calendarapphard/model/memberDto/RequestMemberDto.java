package org.terror.calendarapphard.model.memberDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 클라이언트의 유저 저장,수정 요청을 처리하기위한 DTO 클래스
@AllArgsConstructor
@Getter
public class RequestMemberDto {
    @NotBlank(message = "이름은 공백 일 수 없습니다")
    @Size(max = 10, message = "이름은 최대 10글자까지 작성 할 수 있습니다")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]+$", message = "이름은 한글,영어 소문자,대문자,숫자 만 들어올 수 있습니다")
    private final String author;
    @NotBlank(message = "이메일은 공백 일 수 없습니다")
    @Email(message = "이메일 형식이 아닙니다")
    private final String email;
    @NotBlank(message = "비밀번호는 공백 일 수 없습니다")
    @Size(max = 20, message = "비밀번호는 최대 20글자까지 작성 할 수 있습니다")
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]+$", message = "비밀번호는 영어 소문자,숫자, 특수기호 (!@#$%^&*) 만 들어올 수 있습니다")
    private final String password;
    private final String reqAdminKey;
}
