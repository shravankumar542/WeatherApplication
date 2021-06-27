package com.inshur.weather.WeatherApplication;

import com.inshur.weather.WeatherApplication.client.WeatherAPIClient;
import com.inshur.weather.WeatherApplication.domain.DailyWeather;
import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import com.inshur.weather.WeatherApplication.service.WeatherService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherAPIClient weatherAPIClient;

    @Mock
    private WeatherAPIResponse weatherAPIResponse;


    @Test
    public void getWarmestDay_Test(){

        //when(weatherAPIClient.getWeatherResponse(50.824955973889,-0.13878781625840952)).thenReturn(weatherAPIResponse);

        when(weatherService.getWeatherReportForWeek(50.824955973889,-0.13878781625840952)).thenReturn(weatherAPIResponse);

        Optional<DailyWeather>  dailyWeather= weatherService.getWarmestDay(50.824955973889,-0.13878781625840952);
        Assert.assertNotNull(dailyWeather);

    }

    @Test
    public void getWeatherReportForWeek_Test(){
        when(weatherAPIClient.getWeatherResponse(50.824955973889,-0.13878781625840952)).thenReturn(weatherAPIResponse);
        WeatherAPIResponse apiResponse= weatherService.getWeatherReportForWeek(50.824955973889,-0.13878781625840952);
        Assert.assertNotNull(apiResponse);
        Assert.assertNotNull(apiResponse.getDaily());
    }


}