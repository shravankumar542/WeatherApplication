package com.inshur.weather.WeatherApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inshur.weather.WeatherApplication.controller.WeatherController;
import com.inshur.weather.WeatherApplication.domain.DailyWeather;
import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import com.inshur.weather.WeatherApplication.service.WeatherService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
@RunWith(SpringRunner.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void warmestDayInAWeek_Test_WithValidInputs() throws Exception {
        //String actualResponse =
        mvc.perform(MockMvcRequestBuilders
                .get("/weather/getWarmDayForNextSevenDays")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952")
        ).andExpect(status().isOk()
        ).andExpect(content().contentType("application/json"));

        /*String actualResponse =   mvc.perform(MockMvcRequestBuilders
                .get("/weather/getWarmDay/forNextSevenDays")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952")
        ).andExpect(status().isOk()
        ).andReturn().getResponse()
                .getContentAsString();
        DailyWeather warmestDailyWeather =
                objectMapper.readValue(actualResponse, DailyWeather.class);
        AssertTrue(warmestDailyWeather. */



        /* actualResponse = mvc.perform(MockMvcRequestBuilders
                .get("/weather")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952")
        ).andExpect(status().isOk()
        ).andReturn()
                .getResponse()
                .getContentAsString();
        List<DailyWeather> dailyWeatherList =
                objectMapper.readValue(actualResponse, WeatherAPIResponse.class).getDaily(); */

        //dailyWeatherList.
    }
    @Test
    public void warmestDayInAWeek_Test_WithInvalidInputs() throws Exception {
        //String actualResponse =
        mvc.perform(MockMvcRequestBuilders
                .get("/weather/getWarmDayForNextSevenDays")
                .param("lat", "abc")
                .param("lon", "-0.13878781625840952")
        ).andExpect(status().isBadRequest()
        );
    }

    @Test
    public void getDailyWeatherReportForWeek_Test() throws Exception {
        //String actualResponse =
        mvc.perform(MockMvcRequestBuilders
                .get("/weather/getDailyWeatherNextSevenDays")
                .param("lat", "50.824955973889")
                .param("lon", "-0.13878781625840952")
        ).andExpect(status().isOk());

    }
}