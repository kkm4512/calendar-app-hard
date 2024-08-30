package org.terror.calendarapphard.enums;

import lombok.Getter;

/**
 * 자주 사용되는 URI,PATH 모음
 */
@Getter
public enum UriEnum {
    WEATHER_URI("https://f-api.github.io"),
    WEATHER_PATH("/f-api/weather.json"),
    LOCALHOST("http://localhost:8080"),
    WEATHER_GET_API_PATH("/api/weathers/query");
    private final String uri;

    UriEnum(String uri) {
        this.uri = uri;
    }
}
