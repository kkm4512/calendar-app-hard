package org.terror.calendarapphard.exceptions;

import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * 권한 불일치 예외 클래스
 *
 * 유저의 권한이 일치하지 않을때 발생되는 예외 처리 클래스입니다
 */
public class HandleAuthorityException extends BaseHandleException {
    public HandleAuthorityException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum);
    }
}

