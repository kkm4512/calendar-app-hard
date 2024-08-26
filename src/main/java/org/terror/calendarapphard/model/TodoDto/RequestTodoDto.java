package org.terror.calendarapphard.model.TodoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestTodoDto {
    private String author;
    private String title;
    private String detail;
}
