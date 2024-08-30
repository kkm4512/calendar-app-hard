package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Member,Todo의 중간테이블
 *
 * 캘린더 DB와 소통하는 Entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Calendar extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 일정을 만든 사람
    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

    // 각 일정들
    @ManyToOne
    @JoinColumn(name ="todo_id")
    private Todo todo;

    // 일정을 담당할 사람
    @Column(name = "worker_id")
    private Long workerId;
}
