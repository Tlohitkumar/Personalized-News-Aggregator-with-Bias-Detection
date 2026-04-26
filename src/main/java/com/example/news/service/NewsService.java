package com.example.news.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    private final String API_KEY = "131e3389587c43d8b6d4647545d18f6d";

    @Autowired
    private SentimentService sentimentService;

    public Map<String, Object> getNews(String keyword, String category) {

        String url;

        if (keyword != null && !keyword.isEmpty()) {
            url = "https://newsapi.org/v2/everything?q=" + keyword + "&apiKey=" + API_KEY;
        } 
        else if (category != null && !category.isEmpty()) {
            url = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=" + API_KEY;
        } 
        else {
            url = "https://newsapi.org/v2/everything?q=india&apiKey=" + API_KEY;
        }

        RestTemplate restTemplate = new RestTemplate();

        // 🌐 Get News API response
        Map<String, Object> response =
                restTemplate.getForObject(url, Map.class);

        // 📰 Get articles list
        List<Map<String, Object>> articles =
                (List<Map<String, Object>>) response.get("articles");

        // 🤖 Add AI Report for each article
        for (Map<String, Object> article : articles) {

            String title =
                    article.get("title") != null
                    ? article.get("title").toString()
                    : "";

            String description =
                    article.get("description") != null
                    ? article.get("description").toString()
                    : "";

            String fullText = title + " " + description;

            // 😊 Sentiment + Trust + Summary
            Map ai = sentimentService.analyzeFull(fullText);
            
            article.put("sentiment", ai.get("sentiment"));
            article.put("trust", ai.get("trust"));
            article.put("summary", ai.get("summary"));

            // ⚖️ Bias Detection
            String bias = sentimentService.detectBias(fullText);
            article.put("bias", bias);
            
            String fakeStatus = sentimentService.detectFake(fullText);
            article.put("fakeStatus", fakeStatus);
        }

        return response;
    }
}
