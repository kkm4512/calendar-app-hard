package org.terror.calendarapphard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * DTO 클래스들에 생성일,수정일을 넣어주기위한 DTO 클래스
 *
 * 각 DTO 클래스는 생성일,수정일을 공톹적으로 가지고 있기때문에 하나의 DTO클래스로 묶었다
 */
@AllArgsConstructor
@Getter
public class TimeStampDto {
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
