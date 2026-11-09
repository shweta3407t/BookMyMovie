package com.example.BookMyMovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookMyMovie.models.Movie;
 
 
public interface  MovieRepository extends JpaRepository<  Movie , Long> {

}
