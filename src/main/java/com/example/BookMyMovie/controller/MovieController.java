package com.example.BookMyMovie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.BookMyMovie.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    public MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestParam String movieName,
            @ModelAttribute MultipartFile poster, 
            @RequestParam Long price) {
         

                
        movieService.addMovie(movieName, poster, price);
        return ResponseEntity.ok("Movie added successfully");
    }

}
