package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저 DB와 소통하는 Entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Member extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String email;
    private String password;
    private String role;

    // ERD 설계를 위한 코드 추가
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todoList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Calendar> calendarList = new ArrayList<>();

    //Dto -> Entity
    public Member(RequestMemberDto reqDto) {
        this.author = reqDto.getAuthor();
        this.email = reqDto.getEmail();
        this.password = reqDto.getPassword();
    }

}
