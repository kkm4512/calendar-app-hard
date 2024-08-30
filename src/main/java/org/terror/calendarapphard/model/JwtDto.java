package org.terror.calendarapphard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terror.calendarapphard.entity.Member;

// Jwt에 어떤 내용을 넣어서 만들지 저장하는 DTO 클래스
@AllArgsConstructor
@Getter
public class JwtDto {
    private final Long id;
    private final String author;
    private final String email;
    private final String role;

    public JwtDto(Member member) {
        this.id = member.getId();
        this.author = member.getAuthor();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
