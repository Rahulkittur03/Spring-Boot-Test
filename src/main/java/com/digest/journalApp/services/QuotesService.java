package com.digest.journalApp.services;

import com.digest.journalApp.ApiResponse.QuotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotesService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String QuoteApiKey="https://dummyjson.com/quotes/random";

    public QuotesResponse getQuotes(){
        ResponseEntity<QuotesResponse> exchange = restTemplate.exchange(QuoteApiKey, HttpMethod.GET, null, QuotesResponse.class);
        QuotesResponse body = exchange.getBody();
        return body;
    }



}
