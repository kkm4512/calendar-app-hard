package org.terror.calendarapphard.model.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestMemberDto {
    private String author;
    private String email;
    private String password;
}
