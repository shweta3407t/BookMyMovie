package com.example.BookMyMovie.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class DisplayMovieResponseDTO {
    
    private String name;
    private String posterThumbnailUrl;
    private Long price;
    private String language;
    private LocalDate releaseData;


}
