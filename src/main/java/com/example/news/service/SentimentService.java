package com.example.news.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SentimentService {

    // 😊 Full AI Report
    public Map analyzeFull(String text) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        return restTemplate.postForObject(
                "https://news-python-wi96.onrender.com/sentiment",
                request,
                Map.class
        );
    }

    // ⚖️ Bias Detection
    public String detectBias(String text) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        Map response = restTemplate.postForObject(
                "https://news-python-wi96.onrender.com/bias",
                request,
                Map.class
        );

        return response.get("bias").toString();
    }

    // 🚨 Fake News Detection
    public String detectFake(String text) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        Map response = restTemplate.postForObject(
                "https://news-python-wi96.onrender.com/bias",
                request,
                Map.class
        );

        return response.get("bias").toString();
    }
}