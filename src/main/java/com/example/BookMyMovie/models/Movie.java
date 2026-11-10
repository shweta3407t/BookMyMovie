package com.example.BookMyMovie.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Movie {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    private  String  posterThumbnailUrl ;

    private Long price;
 

    public Movie(Long id, String name, String posterThumbnailUrl, Long price) {
        this.id = id;
        this.name = name;
        this.posterThumbnailUrl = posterThumbnailUrl;
        this.price = price;
    }

    public Movie(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterThumbnailUrl() {
        return posterThumbnailUrl;
    }

    public void setPosterThumbnailUrl(String posterThumbnailUrl) {
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    
    


}
