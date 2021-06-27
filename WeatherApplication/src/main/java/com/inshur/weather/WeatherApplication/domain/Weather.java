package com.inshur.weather.WeatherApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Weather {
	int id;
	String main;
	String description;
	String icon;
}
