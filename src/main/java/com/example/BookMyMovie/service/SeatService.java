package com.example.BookMyMovie.service;

import org.springframework.stereotype.Service;

import com.example.BookMyMovie.models.Seat;

@Service
public class TheaterService {

    public Seat[][] displaySeat() {

        int rows = 12;
        int columns = 15;
        Seat[][] seatArray = new Seat[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String seatNumber = (char) ('A' + i) + String.valueOf(j + 1);
                String seatType = ( i <= 5  || j== i  ) ? "Gold" : " Silver";

                seatArray[i][j] = new Seat(seatNumber, seatType);

            }
        }

        Seat seat = seatArray[0][0];
        System.out.println("Booked Seat: " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");

        return seatArray;

    }

}
