package com.example.news.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    private final String API_KEY = "131e3389587c43d8b6d4647545d18f6d";

    public String getNews() {
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}