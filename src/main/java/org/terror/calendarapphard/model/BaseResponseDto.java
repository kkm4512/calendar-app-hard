package org.terror.calendarapphard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terror.calendarapphard.enums.BaseResponseEnum;

// 서버에서 처리된 응답을 클라이언트로 보내기위한 DTO 클래스
@Getter
@AllArgsConstructor
public class BaseResponseDto {
    private final boolean isOk;
    private final int status;
    private final String message;

    //BaseResponseEnum 의 상태를 클라이언트로 던져주기위한 생성자
    public BaseResponseDto(BaseResponseEnum baseResponseEnum) {
        this.isOk = baseResponseEnum.isOK();
        this.status = baseResponseEnum.getStatus();
        this.message = baseResponseEnum.getMessage();
    }
}
