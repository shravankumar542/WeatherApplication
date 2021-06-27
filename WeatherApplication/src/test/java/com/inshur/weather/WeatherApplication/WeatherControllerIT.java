package com.inshur.weather.WeatherApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inshur.weather.WeatherApplication.domain.DailyWeather;
import com.inshur.weather.WeatherApplication.domain.Temp;
import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WeatherApplication.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WeatherControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void getWarmestDayInaWeek_IT() throws Exception {

        String actualResponse = mvc.perform(MockMvcRequestBuilders
                .get("/weather/getWarmDayForNextSevenDays")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952"))
                .andExpect(status().isOk()
                ).andReturn()
                .getResponse()
                .getContentAsString();

        Temp temparature = objectMapper.readValue(actualResponse, DailyWeather.class).getTemp();

        assertNotNull(temparature);

    }

    @Test
    public void getDailyWeatherReportForWeek_IT() throws Exception {

        String actualResponse = mvc.perform(MockMvcRequestBuilders
                .get("/weather/getDailyWeatherNextSevenDays")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952"))
                .andExpect(status().isOk()
                ).andReturn()
                .getResponse()
                .getContentAsString();

        List<DailyWeather> dailyWeatherList = objectMapper.readValue(actualResponse, WeatherAPIResponse.class).getDaily();

        assertNotNull(dailyWeatherList);
        assertEquals(8,dailyWeatherList.size());

    }
}