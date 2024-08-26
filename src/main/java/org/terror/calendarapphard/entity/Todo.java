package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.TodoDto.RequestTodoDto;

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

    // 사용자로부터 요청이 들어왔을때 일정 저장 시키기 위한 생성자
    public Todo(RequestTodoDto reqTodo) {
        this.title = reqTodo.getTitle();
        this.author = reqTodo.getAuthor();
        this.detail = reqTodo.getDetail();
    }
}
