package org.terror.calendarapphard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.todoDto.RequestTodoDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Todo extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String detail;

    // 댓글 갯수 알려면 양방향 해야함
    // One 관계인 부모가 삭제되면 자식 관계인 Comment 도 삭제됨
    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private final List<Comment> commnetList = new ArrayList<>();

    // 일정은 이제 작성 유저명 대신 유저 고유 식별자 필드를 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Todo 가 삭제되면 캘린더도 삭제되게 하기위해 설정
    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Calendar> calendarList = new ArrayList<>();


    // Dto -> Entity
    public Todo(RequestTodoDto reqTodo) {
        this.title = reqTodo.getTitle();
        this.detail = reqTodo.getDetail();
    }
}
