package com.inshur.weather.WeatherApplication.service;

import com.inshur.weather.WeatherApplication.client.WeatherAPIClient;
import com.inshur.weather.WeatherApplication.domain.DailyWeather;
import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@Slf4j
public class WeatherService {
    @Autowired
    private WeatherAPIClient weatherAPIClient;

    public Optional<DailyWeather> getWarmestDay(Double lat,
                                                Double lon){
        WeatherAPIResponse weatherAPIResponse= getWeatherReportForWeek(lat,lon);

        log.debug("weatherAPIResponse:: {}",weatherAPIResponse.toString());
        log.info("daily size {}",weatherAPIResponse.getDaily().size());

        //comparator for highestTemp
        Comparator<DailyWeather> byHighestTemparature =
                (d1,d2) -> Double.compare(d1.getTemp().getMax(), d2.getTemp().getMax());

        Comparator<DailyWeather> byLowestHumidity =
                Comparator.comparing(DailyWeather::getHumidity);

        Optional<DailyWeather> dailyWeather= weatherAPIResponse.getDaily().stream()
                .sorted(byHighestTemparature.reversed() //highest temprature compare
                        .thenComparing(byLowestHumidity)) //lowest humdity compare
                .findFirst(); //fetch first record off it
        log.debug("dailyWeather {}",dailyWeather.toString());
        return dailyWeather;
    }

    public WeatherAPIResponse getWeatherReportForWeek(Double lat,
                                                      Double lon) {
        return weatherAPIClient.getWeatherResponse(lat,lon);
    }
}