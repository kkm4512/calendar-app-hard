package org.terror.calendarapphard.waether.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.terror.calendarapphard.enums.UriEnum;
import org.terror.calendarapphard.waether.model.ResponseWeatherDto;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    public String getWeather(String today) {
        URI uri = UriComponentsBuilder
                .fromUriString(UriEnum.WEATHER_URI.getUri())
                .path(UriEnum.WEATHER_PATH.getUri())
                .build()
                .toUri();
        ResponseEntity<String> resEntity = restTemplate.getForEntity(uri, String.class);
        Map<String,String> weatherMap = fromJSONtoWeatherMap(resEntity.getBody());
        return weatherMap.get(today);
    }

    private Map<String,String> fromJSONtoWeatherMap(String resEntity) {
        Map<String,String> weatherMap = new HashMap<>();
        JSONArray weathers = new JSONArray(resEntity);
        for ( Object weather : weathers ) {
            ResponseWeatherDto responseWeatherDto = new ResponseWeatherDto((JSONObject) weather);
            weatherMap.put(responseWeatherDto.getDate(), responseWeatherDto.getWeather());
        }
        return weatherMap;
    }
}
