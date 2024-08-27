package org.terror.calendarapphard.model.memberDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.model.TimeStampDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseMemberDto extends TimeStampDto {
    private Long id;
    private String author;
    private String email;

    // Entity -> Dto
    public ResponseMemberDto(Member member) {
        this.id = member.getId();
        this.author = member.getAuthor();
        this.email = member.getEmail();
        this.setCreatedAt(member.getCreatedAt());
        this.setUpdatedAt(member.getUpdatedAt());
    }
}
