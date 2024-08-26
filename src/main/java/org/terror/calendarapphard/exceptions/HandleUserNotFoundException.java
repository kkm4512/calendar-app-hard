package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
public class HandleUserNotFoundException extends RuntimeException {
    BaseResponseEnum baseResponseEnum;

    public HandleUserNotFoundException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum.getMessage());
        this.baseResponseEnum = baseResponseEnum;
    }
}
