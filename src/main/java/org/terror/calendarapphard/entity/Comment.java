package org.terror.calendarapphard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 댓글 DB와 소통하는 Entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Comment extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detail;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

}
