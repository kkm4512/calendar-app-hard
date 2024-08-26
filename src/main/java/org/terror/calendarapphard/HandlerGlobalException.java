package org.terror.calendarapphard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleUserNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;

@RestControllerAdvice
public class HandlerGlobalException {
    @ExceptionHandler(HandleUserNotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(HandleUserNotFoundException e) {
        BaseResponseEnum status = e.getBaseResponseEnum();
        return ResponseEntity.status(status.getStatus()).body(new BaseResponseDto(status));
    }
}
