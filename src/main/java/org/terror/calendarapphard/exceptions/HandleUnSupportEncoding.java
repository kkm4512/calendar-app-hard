package org.terror.calendarapphard.exceptions;

import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * 인코딩 형식 지원 불가 예외 클래스
 *
 * 인코딩 형식 불일치시 처리할 에외 처리 클래스 입니다
 */
public class HandleUnSupportEncoding extends BaseHandleException {
    public HandleUnSupportEncoding(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum);
    }
}
