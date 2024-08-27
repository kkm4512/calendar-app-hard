package org.terror.calendarapphard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terror.calendarapphard.entity.Member;

@AllArgsConstructor
@Getter
public class JwtDto {
    private Long id;
    private String author;
    private String email;

    public JwtDto(Member member) {
        this.id = member.getId();
        this.author = member.getAuthor();
        this.email = member.getEmail();
    }
}
