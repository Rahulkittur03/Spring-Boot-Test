package com.digest.journalApp.services;

import com.digest.journalApp.ApiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    public static final String WeatherKey="154ab042e329ac8f28661fc6eb1659f8";
    public static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    @Autowired
    private RestTemplate restTemplate;


    public WeatherResponse getWeather(String city)
    {
        String finalApi= API.replace("API_KEY",WeatherKey).replace("CITY",city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
