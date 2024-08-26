package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.CommentDto.RequestCommentDto;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String detail;

    // N:1 관계 설정
    // 하나의 Todo는 여러개의 Comment를 가질 수 있기 떄문
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;


    //Dto -> Entity
    public Comment(RequestCommentDto reqDto) {
        this.author = reqDto.getAuthor();
        this.detail = reqDto.getDetail();
    }
}
