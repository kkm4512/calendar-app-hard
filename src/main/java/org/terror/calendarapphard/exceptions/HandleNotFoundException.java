package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
// 무언가 찾는게 없을때
public class HandleNotFoundException extends RuntimeException {
    BaseResponseEnum baseResponseEnum;
    public HandleNotFoundException(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum.getMessage());
        this.baseResponseEnum = baseResponseEnum;
    }
}

