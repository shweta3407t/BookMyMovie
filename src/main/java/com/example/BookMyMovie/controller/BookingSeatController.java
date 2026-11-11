package com.example.BookMyMovie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookMyMovie.service.TheaterService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController 
@RequestMapping ("/api/movie/seat")
public class BookingSeatController {

    @Autowired 
    public TheaterService theaterService;


    @PostMapping("/booking")
    public String bookingSeat(@RequestParam  String entity) {
        //TODO: process POST request
        
        return entity;
    }


    @GetMapping("/display")
    public  ResponseEntity<String>  displaySeat() {
        
        theaterService.displaySeat();


        return ResponseEntity.ok( "display seat ");
    }
    
    

}
