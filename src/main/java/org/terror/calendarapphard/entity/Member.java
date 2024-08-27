package org.terror.calendarapphard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Member extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String email;

    //Dto -> Entity
    public Member(RequestMemberDto reqDto) {
        this.author = reqDto.getAuthor();
        this.email = reqDto.getEmail();
    }
}
