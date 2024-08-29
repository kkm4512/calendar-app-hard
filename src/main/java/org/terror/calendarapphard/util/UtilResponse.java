package org.terror.calendarapphard.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;

import java.nio.charset.StandardCharsets;

public class UtilResponse {
    private static final HttpHeaders headers = new HttpHeaders();
    // ResponseEntity 형식으로 반환시켜주는 메서드
    public static ResponseEntity<BaseResponseDto> getResponseEntity(BaseResponseEnum baseResponseEnum) {

        // 상태
        int status =  baseResponseEnum.getStatus();

        // 헤더
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        // 응답 데이터
        BaseResponseDto responseData = new BaseResponseDto(baseResponseEnum);

        return new ResponseEntity<>( responseData, headers,  status );
    }
}
