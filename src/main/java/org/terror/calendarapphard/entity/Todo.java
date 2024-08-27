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
    private String author;
    private String detail;

    // 댓글 갯수 알려면 양방향 해야함
    // One 관계인 부모가 삭제되면 자식 관계인 Comment 도 삭제됨
    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private final List<Comment> commnetList = new ArrayList<>();

    // Dto -> Entity
    public Todo(RequestTodoDto reqTodo) {
        this.title = reqTodo.getTitle();
        this.author = reqTodo.getAuthor();
        this.detail = reqTodo.getDetail();
    }
}
