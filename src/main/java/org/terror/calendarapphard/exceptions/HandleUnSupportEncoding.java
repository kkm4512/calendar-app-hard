package org.terror.calendarapphard.exceptions;

import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
// 인코딩 형식이 지원하지 않을때
public class HandleUnSupportEncoding extends RuntimeException {
    BaseResponseEnum baseResponseEnum;
    public HandleUnSupportEncoding(BaseResponseEnum baseResponseEnum) {
        super(baseResponseEnum.getMessage());
        this.baseResponseEnum = baseResponseEnum;
    }
}
