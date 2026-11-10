package com.example.BookMyMovie.models;

 import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.annotation.Generated;
 import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
public class Seat {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    private String seatType;

     private SeatStatus status;

 
 

}
