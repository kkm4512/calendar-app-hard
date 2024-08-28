package org.terror.calendarapphard.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UtilDate {
    // 오늘날짜 08-28 형식으로 생성
    public String getTodayFormat(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }
}
