package org.terror.calendarapphard.model.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInDto {
    private String email;
    private String password;
}
