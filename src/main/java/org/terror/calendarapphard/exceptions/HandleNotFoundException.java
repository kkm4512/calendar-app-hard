package org.terror.calendarapphard.exceptions;

import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * 조회 불가 예외 클래스
 *
 * 조회 하고자 하는 데이터가 없을경우 예외 처리 클래스입니다
 */
public class HandleNotFoundException extends BaseHandleException {
    public HandleNotFoundException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum);
    }
}

