package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

/**
 * 공통 예외 처리 클래스
 *
 * 여러 예외 클래스에서 공통적으로 사용되는 로직 정의 부모 클래스
 */
@Getter
public class BaseHandleException extends RuntimeException {
    BaseResponseEnum baseResponseEnum;
    public BaseHandleException(BaseResponseEnum baseResponseEnum) {
        this.baseResponseEnum = baseResponseEnum;
    }
}
