package org.terror.calendarapphard.entity;

import jakarta.persistence.*;
import lombok.*;

//Member 와 todos 중간 테이블
@NoArgsConstructor
@AllArgsConstructor
@Builder //setter 사용시, 외부에서 정보 변경의 위험이 있기때문에, 빌더로 데이터 주입
@Setter // 일정을 작성한 유저는, 해당 일정의 담당자를 배치 시킬 수 있기떄문에, setter 사용
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
