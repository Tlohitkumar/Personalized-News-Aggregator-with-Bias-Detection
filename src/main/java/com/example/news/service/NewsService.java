package com.example.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class NewsService {

    private final String API_KEY = "131e3389587c43d8b6d4647545d18f6d";

    @Autowired
    private SentimentService sentimentService;

    public Map<String, Object> getNews(String keyword, String category) {

        String url;

        if (keyword != null && !keyword.isEmpty()) {
            url = "https://newsapi.org/v2/everything?q=" + keyword + "&apiKey=" + API_KEY;
        } else if (category != null && !category.isEmpty()) {
            url = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=" + API_KEY;
        } else {
            url = "https://newsapi.org/v2/everything?q=india&apiKey=" + API_KEY;
        }

        RestTemplate restTemplate = new RestTemplate();

        // 🔥 Convert response to Map
        Map response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> articles = (List<Map<String, Object>>) response.get("articles");

        // 🔥 Add sentiment
        for (Map<String, Object> article : articles) {

            String title = (String) article.get("title");
            String description = (String) article.get("description");

            String bias = sentimentService.detectBias(title + " " + description);
            article.put("bias", bias);
        }

        return response;
    }
}