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
                "http://localhost:5000/sentiment",
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
                "http://localhost:5000/bias",
                request,
                Map.class
        );

        return response.get("bias").toString();
    }
    
    public String detectFake(String text) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        Map response = restTemplate.postForObject(
                "http://localhost:5000/fakecheck",
                request,
                Map.class
        );

        return response.get("fakeStatus").toString();
    }
}