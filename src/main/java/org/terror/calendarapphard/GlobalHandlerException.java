package org.terror.calendarapphard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(HandleNotFoundException e) {
        BaseResponseEnum status = e.getBaseResponseEnum();
        return ResponseEntity.status(status.getStatus()).body(new BaseResponseDto(status));
    }
}
