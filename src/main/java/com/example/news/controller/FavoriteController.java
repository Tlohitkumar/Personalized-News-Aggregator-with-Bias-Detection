package com.example.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.news.model.Favorite;
import com.example.news.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private FavoriteService service;

    @PostMapping
    public Favorite save(@RequestBody Favorite fav) {
        return service.save(fav);
    }

    @GetMapping("/{email}")
    public List<Favorite> get(@PathVariable String email) {
        return service.getByUser(email);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }
}