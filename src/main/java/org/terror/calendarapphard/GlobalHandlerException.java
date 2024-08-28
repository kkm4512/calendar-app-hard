package org.terror.calendarapphard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleDuplicateException;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;

@RestControllerAdvice
public class GlobalHandlerException {
    // 무언갈 못찾을때 나오는 exception
    @ExceptionHandler(HandleNotFoundException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(HandleNotFoundException e) {
        BaseResponseEnum status = e.getBaseResponseEnum();
        return ResponseEntity.status(status.getStatus()).body(new BaseResponseDto(status));
    }

    // DTO exception custom
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(MethodArgumentNotValidException e) {
        FieldError fe = e.getBindingResult().getFieldError();
        BaseResponseDto baseResponseDto = new BaseResponseDto(false, HttpStatus.BAD_REQUEST.value(),fe.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(baseResponseDto);
    }

    // 무언가 중복됐을때 나타내는 exception
    @ExceptionHandler(HandleDuplicateException.class)
    public ResponseEntity<BaseResponseDto> handleNotFoundException(HandleDuplicateException e) {
        BaseResponseEnum status = e.getBaseResponseEnum();
        return ResponseEntity.status(status.getStatus()).body(new BaseResponseDto(status));
    }
}






