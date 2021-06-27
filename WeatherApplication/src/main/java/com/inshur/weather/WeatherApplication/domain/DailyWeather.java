package com.inshur.weather.WeatherApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailyWeather {
    ZonedDateTime dt;
    ZonedDateTime sunrise;
    ZonedDateTime moonrise;
    ZonedDateTime moonset;
    double moon_phase;
    Temp temp;
    FeelsLike feels_like;
    int pressure;
    int humidity;
    double dew_point;
    double wind_speed;
    int wind_deg;
    double wind_gust;
    List<Weather> weather;
    int clouds;
    double pop;
    double rain;
    double uvi;


}
