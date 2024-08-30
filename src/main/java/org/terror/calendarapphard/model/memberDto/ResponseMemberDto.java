package org.terror.calendarapphard.model.memberDto;

import lombok.Getter;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.model.TimeStampDto;

// 서버에서 처리된 유저를 클라이언트로 보내기위한 DTO 클래스
@Getter
public class ResponseMemberDto extends TimeStampDto {
    private Long id;
    private String author;
    private String email;

    public ResponseMemberDto(Member member) {
        super(member.getCreatedAt(), member.getUpdatedAt());
        this.id = member.getId();
        this.author = member.getAuthor();
        this.email = member.getEmail();
    }
}
