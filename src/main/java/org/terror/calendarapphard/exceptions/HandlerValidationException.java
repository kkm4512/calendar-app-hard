package org.terror.calendarapphard.exceptions;

import lombok.Getter;

@Getter
// DTO exception 직접 커스텀하기
public class HandlerValidationException extends RuntimeException {
    private String message;
    public HandlerValidationException(String message) {
        this.message = message;
    }
}

