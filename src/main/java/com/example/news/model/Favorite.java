package com.example.news.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String imageUrl;
    private String userEmail;

    public Favorite() {}

    public Favorite(Long id, String title, String url, String imageUrl, String userEmail) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.userEmail = userEmail;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getImageUrl() { return imageUrl; }
    public String getUserEmail() { return userEmail; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setUrl(String url) { this.url = url; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}