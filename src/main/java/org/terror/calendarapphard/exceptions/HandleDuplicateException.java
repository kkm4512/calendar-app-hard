package org.terror.calendarapphard.exceptions;

import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * 유저 중복 예외 클래스
 *
 * 유저의 이메일이 중복됐을때 발생되는 예외 처리 클래스입니다
 */
public class HandleDuplicateException extends BaseHandleException {
    public HandleDuplicateException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum);
    }
}

