package org.terror.calendarapphard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

@Getter
@AllArgsConstructor
public class BaseResponseDto {
    private boolean isGood;
    private int status;
    private String message;

    //BaseResponseEnum 의 상태를 클라이언트로 던져주기위한 생성자
    public BaseResponseDto(BaseResponseEnum baseResponseEnum) {
        this.isGood = baseResponseEnum.isOK();
        this.status = baseResponseEnum.getStatus();
        this.message = baseResponseEnum.getMessage();
    }
}
