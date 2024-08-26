package org.terror.calendarapphard.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeStampDto {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
