package com.inshur.weather.WeatherApplication.client;

import com.inshur.weather.WeatherApplication.domain.WeatherAPIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherAPIClient {

    @Value("${exclude.param}")
    String excludeParameters;

    @Value("${weather.appId}")
    String appId;

    @Value("${weather.api}")
    String url;

    @Value("${weather.units}")
    String units;

    @Autowired
    RestTemplate restTemplate;


    public WeatherAPIResponse getWeatherResponse(Double lat,
                                                 Double lon){
        log.info("query parameters passed are");
        log.info("longitude {} latitude {} excludes {} appId {} url {}",lat,lon,excludeParameters,appId,url);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", excludeParameters)
                .queryParam("appId", appId)
                .queryParam("units",units);

        HttpEntity<?> entity = new HttpEntity<>(requestHeaders);

        HttpEntity<WeatherAPIResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherAPIResponse.class);

        log.debug("response data::{}",response.getBody());

        return response.getBody();
    }
}