package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
// 권한이 맞지 않을때
public class HandleDuplicateException extends RuntimeException {
    BaseResponseEnum baseResponseEnum;
    public HandleDuplicateException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum.getMessage());
        this.baseResponseEnum = baseResponseEnum;
    }
}

