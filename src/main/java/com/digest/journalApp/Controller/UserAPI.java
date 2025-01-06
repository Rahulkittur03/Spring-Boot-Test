package com.digest.journalApp.Controller;

import com.digest.journalApp.services.QuotesService;
import com.digest.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API")


public class UserAPI {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private QuotesService quotesService;

    @GetMapping()
    public ResponseEntity<?> greeting()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi "+authentication.getName()+ weatherService.getWeather("Delhi").getCurrent().getTemperature(),HttpStatus.OK);
    }
    @GetMapping("/quotes")
    public ResponseEntity<?> Quotes()
    {
        return new ResponseEntity<>(quotesService.getQuotes().getQuote(),HttpStatus.OK);
    }
}
