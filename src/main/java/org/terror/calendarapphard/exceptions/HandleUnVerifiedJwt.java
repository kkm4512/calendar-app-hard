package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
public class HandleUnVerifiedJwt extends RuntimeException {
    BaseResponseEnum baseResponseEnum;
    public HandleUnVerifiedJwt(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum.getMessage());
        this.baseResponseEnum = baseResponseEnum;
    }
}
