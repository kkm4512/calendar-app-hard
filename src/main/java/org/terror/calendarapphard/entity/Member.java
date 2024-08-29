package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;

import java.util.ArrayList;
import java.util.List;

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
    private String password;
    private String role;

    // ERD 설계를 위한 코드 추가
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Todo> todoList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Calendar> calendarList = new ArrayList<>();

    //Dto -> Entity
    public Member(RequestMemberDto reqDto) {
        this.author = reqDto.getAuthor();
        this.email = reqDto.getEmail();
        this.password = reqDto.getPassword();
    }

}
