package org.terror.calendarapphard.waether.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseWeatherDto {
    String date;
    String weather;

    public ResponseWeatherDto(JSONObject json) {
        this.date = json.optString("date");
        this.weather = json.optString("weather");
    }
}
