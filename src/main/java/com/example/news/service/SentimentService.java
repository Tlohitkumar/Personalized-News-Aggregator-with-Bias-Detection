package com.example.news.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SentimentService {

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
}