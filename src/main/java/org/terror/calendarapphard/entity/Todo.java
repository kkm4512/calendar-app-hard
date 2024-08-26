package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.terror.calendarapphard.model.TodoDto.RequestTodoDto;

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

    // Dto -> Entity
    public Todo(RequestTodoDto reqTodo) {
        this.title = reqTodo.getTitle();
        this.author = reqTodo.getAuthor();
        this.detail = reqTodo.getDetail();
    }
}
