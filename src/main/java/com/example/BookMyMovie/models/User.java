package com.example.BookMyMovie.models;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Entity 
public class User {

    private String name ;
    private String password;
    private  String email;

    

}
