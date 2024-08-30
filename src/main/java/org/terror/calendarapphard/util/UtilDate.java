package org.terror.calendarapphard.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날짜와 관련된 유틸 메서드 모음
 */
@Component
public class UtilDate {
    /**
     * 오늘 날짜를 포맷팅하여 반환 메서드
     *
     * @return String / 오늘 날짜를 포맷팅하여 반환
     *
     * 오늘날짜가 2024-08-30 이라면 08-30으로 반환합니다
     */
    public String getTodayFormat(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }
}
