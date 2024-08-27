package org.terror.calendarapphard.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseEnum {
    // 공용
    SUCCESS(true, HttpStatus.OK.value(),"요청에 성공 하였습니다"),
    FAIL(false,HttpStatus.BAD_REQUEST.value(), "요청에 실패 하였습니다"),

    // User
    USER_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "요청 하는 유저가 존재하지 않습니다"),

    // Todo
    TODO_SAVE_SUCCESS(true, HttpStatus.OK.value(),"일정 저장에 성공 하였습니다"),
    TODO_UPDATE_SUCCESS(true, HttpStatus.OK.value(),"일정 수정에 성공 하였습니다"),
    TODO_DELETE_SUCCESS(true, HttpStatus.OK.value(),"일정 삭제에 성공 하였습니다"),

    // Comment
    COMMENT_SAVE_SUCCESS(true, HttpStatus.OK.value(),"댓글 저장에 성공 하였습니다"),
    COMMENT_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "요청 하는 댓글이 존재하지 않습니다"),
    COMMENT_UPDATE_SUCCESS(true, HttpStatus.OK.value(),"댓글 수정에 성공 하였습니다"),
    COMMENT_DELETE_SUCCESS(true, HttpStatus.OK.value(),"댓글 삭제에 성공 하였습니다");



    private boolean isOK;
    private int status;
    private String message;

    BaseResponseEnum(boolean isOK, int status, String message) {
        this.isOK = isOK;
        this.status = status;
        this.message = message;
    }
}
