package com.example.BookMyMovie.entity;

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

    public Movie(Long id, String name, String posterThumbnailUrl) {
        this.id = id;
        this.name = name;
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

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

    

    
     






}
