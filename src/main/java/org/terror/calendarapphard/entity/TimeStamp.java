package org.terror.calendarapphard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 서버에서 저장되는 생성일,수정일을 처리하기위한 DTO 클래스
 *
 * 해당 DTO클래스를 상속받고, 생성하거나 수정하면 자동으로 DB가 업데이트 됩니다
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeStamp {
    @CreatedDate  // 데이터 생성 날짜를 자동으로 주입
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate  // 데이터 수정 날짜를 자동으로 주입
    private LocalDateTime updatedAt;
}
