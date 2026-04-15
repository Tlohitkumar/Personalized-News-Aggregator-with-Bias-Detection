package com.example.news.controller;


import com.example.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

//    @GetMapping
//    public String getNews() {
//        return newsService.getNews();
//    }
    
    @GetMapping
    public String getNews(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {

        return newsService.getNews(keyword, category);
    }
}