package com.digest.journalApp.ApiResponse;

import lombok.Data;

@Data
public class QuotesResponse{
    private int id;
    private String quote;
    private String author;
}
