package com.digest.journalApp.services;

import com.digest.journalApp.ApiResponse.QuotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuotesService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${Api.Key.quotes}")
    private String QuoteApiKey;

    public QuotesResponse getQuotes(){
        ResponseEntity<QuotesResponse> exchange = restTemplate.exchange(QuoteApiKey, HttpMethod.GET, null, QuotesResponse.class);
        QuotesResponse body = exchange.getBody();
        return body;
    }



}
