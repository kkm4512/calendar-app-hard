package org.terror.calendarapphard.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseEnum {
    // 공용
    SUCCESS(true, HttpStatus.OK.value(),"요청에 성공 하였습니다"),
    FAIL(false,HttpStatus.BAD_REQUEST.value(), "요청에 실패 하였습니다"),

    // Member
    MEMBER_SAVE_SUCCESS(true,HttpStatus.OK.value(), "유저 저장에 성공 하였습니다"),
    MEMBER_DELETE_SUCCESS(true,HttpStatus.OK.value(), "유저 삭제에 성공 하였습니다"),
    MEMBER_LOGIN_SUCCESS(true,HttpStatus.OK.value(), "로그인에 성공 하였습니다"),
    MEMBER_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "요청 하는 유저가 존재하지 않습니다"),
    MEMBER_INVALID_CREDENTIALS(false,HttpStatus.UNAUTHORIZED.value(),"아이디 또는 비밀번호가 틀렸습니다"),

    // Worker
    WORKER_SET_SUCCESS(true,HttpStatus.OK.value(), "담당자 배치에 성공 하였습니다"),

    // Todo
    TODO_SAVE_SUCCESS(true, HttpStatus.OK.value(),"일정 저장에 성공 하였습니다"),
    TODO_UPDATE_SUCCESS(true, HttpStatus.OK.value(),"일정 수정에 성공 하였습니다"),
    TODO_DELETE_SUCCESS(true, HttpStatus.OK.value(),"일정 삭제에 성공 하였습니다"),
    TODO_NOT_FOUND(false, HttpStatus.NOT_FOUND.value(),"요청 하는 일정이 존재하지 않습니다"),

    // Comment
    COMMENT_SAVE_SUCCESS(true, HttpStatus.OK.value(),"댓글 저장에 성공 하였습니다"),
    COMMENT_UPDATE_SUCCESS(true, HttpStatus.OK.value(),"댓글 수정에 성공 하였습니다"),
    COMMENT_DELETE_SUCCESS(true, HttpStatus.OK.value(),"댓글 삭제에 성공 하였습니다"),
    COMMENT_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(), "요청 하는 댓글이 존재하지 않습니다"),

    // Calendar
    CALENDAR_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(),"요청 하는 캘린더는 존재하지 않습니다"),

    // Jwt
    JWT_NOT_FOUND(false,HttpStatus.NOT_FOUND.value(),"Jwt가 존재하지 않습니다"),
    JWT_EXPIRED(false,HttpStatus.UNAUTHORIZED.value(),"Jwt가 만료되었습니다"),
    JWT_MALFORMED(false,HttpStatus.UNAUTHORIZED.value(),"Jwt가 손상 되었습니다"),
    JWT_UNSUPPORTED(false,HttpStatus.UNAUTHORIZED.value(), "지원되지 않는 Jwt 입니다"),
    JWT_SIGNATURE_FAIL(false,HttpStatus.UNAUTHORIZED.value(), "시그니처 검증에 실패한 Jwt 입니다"),

    // Auth
    NOT_ADMIN(false,HttpStatus.FORBIDDEN.value(), "관리자만 접속 가능 합니다"),

    // Encode
    UNSUPPORTED_ENCODING(false,HttpStatus.BAD_REQUEST.value(), "클라이언트로 부터 들어온 형식은, 인코딩 할 수 없는 형식 입니다");


    private boolean isOK;
    private int status;
    private String message;

    BaseResponseEnum(boolean isOK, int status, String message) {
        this.isOK = isOK;
        this.status = status;
        this.message = message;
    }
}
