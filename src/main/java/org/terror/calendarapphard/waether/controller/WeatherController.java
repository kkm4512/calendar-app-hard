package org.terror.calendarapphard.waether.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.terror.calendarapphard.waether.service.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weathers")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/query")
    public String getWeather(@RequestParam String today){
        return weatherService.getWeather(today);
    }
}
