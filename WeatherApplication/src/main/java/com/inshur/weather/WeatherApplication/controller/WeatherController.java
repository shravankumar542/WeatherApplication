package com.inshur.weather.WeatherApplication.controller;


import com.inshur.weather.WeatherApplication.domain.DailyWeather;
import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import com.inshur.weather.WeatherApplication.service.WeatherService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@Api(tags = "Weather API")
@Slf4j
@Validated
@RequestMapping(path = "/weather", produces= MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @ApiOperation(value = "Gets the Warmest Day in next 7 days.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "Location LATITUDE value",
                    required = true, dataType = "Double", paramType = "query", defaultValue = "50.824955973889"),
            @ApiImplicitParam(name = "lon", value = "Location LONGITUDE value",
                    required = true, dataType = "Double", paramType = "query", defaultValue = "-0.13878781625840952")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DailyWeather.class),
            @ApiResponse(code = 400, message = "Invalid or missing request data."),
            @ApiResponse(code = 500, message = "Internal Server Error")}) //, response = ErrorResponse.class

    @RequestMapping(value = "/getWarmDayForNextSevenDays",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<DailyWeather> getWarmestDayInaWeek(
                                                        @RequestParam(required = true)
                                                        @Valid Double lat,
                                                        @RequestParam(required = true)
                                                        @Valid Double lon){
        log.info("lat::"+lat+" lon::"+lon);
        return weatherService.getWarmestDay(lat,lon);


    }
    @ApiOperation(value = "Gets the Weather data for next 7 days.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "Location LATITUDE value",
                    required = true, dataType = "Double", paramType = "query", defaultValue = "50.824955973889"),
            @ApiImplicitParam(name = "lon", value = "Location LONGITUDE value",
                    required = true, dataType = "Double", paramType = "query", defaultValue = "-0.13878781625840952")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = WeatherAPIResponse.class),
            @ApiResponse(code = 400, message = "Invalid or missing request data."),
            @ApiResponse(code = 500, message = "Internal Server Error")}) //, response = ErrorResponse.class
    @GetMapping(value = "/getDailyWeatherNextSevenDays",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WeatherAPIResponse getDailyWeatherReportForWeek(@RequestParam(required = true)
                                                           @Valid Double lat,
                                                           @RequestParam(required = true)
                                                           @Valid Double lon){
        log.info("lat::"+lat+" lon::"+lon);
        return weatherService.getWeatherReportForWeek(lat,lon);


    }

}