package com.inshur.weather.WeatherApplication.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherAPIResponse {
	
	BigDecimal lat;
	BigDecimal lon;
	String timezone;
	int timezone_offset;
	List<DailyWeather> daily;
}
