package com.digest.journalApp.services;

import com.digest.journalApp.ApiResponse.WeatherResponse;
import com.digest.journalApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${Api.Key.weather}")
    public String WeatherKey;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city)
    {

        WeatherResponse weatherResponse = redisService.get(city, WeatherResponse.class);
       if(weatherResponse!=null)
       {
           return weatherResponse;
       }
       else {
           String finalApi= appCache.APPCACHE.get("Weather_Api").replace("<API_KEY>",WeatherKey).replace("<CITY>",city);
           ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
           WeatherResponse body = response.getBody();
           if(body!=null)
           {
               redisService.set(city,body,300l);
           }
           return body;
       }
    }

}
