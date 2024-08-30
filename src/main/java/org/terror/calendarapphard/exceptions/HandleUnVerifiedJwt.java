package org.terror.calendarapphard.exceptions;

import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * Jwt 미유효 예외 클래스
 *
 * Jwt가 파손,시그니처 검증실패,만료기간 지남 등의 예외 처리 클래스 입니다
 */
public class HandleUnVerifiedJwt extends BaseHandleException {
    public HandleUnVerifiedJwt(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum);

    }
}
