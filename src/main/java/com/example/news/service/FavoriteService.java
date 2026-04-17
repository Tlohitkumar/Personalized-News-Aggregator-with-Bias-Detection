package com.example.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.news.model.Favorite;
import com.example.news.repository.FavoriteRepository;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repo;

    public Favorite save(Favorite fav) {
        return repo.save(fav);
    }

    public List<Favorite> getByUser(String email) {
        return repo.findByUserEmail(email);
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }
}