package com.example.BookMyMovie.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BookMyMovie.dto.CustomMultipartFile;
import com.example.BookMyMovie.dto.DisplayMovieResponseDTO;
import com.example.BookMyMovie.models.Movie;
import com.example.BookMyMovie.repository.MovieRepository;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class MovieService {

    @Autowired
    public S3StorageService s3StorageService;

    @Autowired
    public MovieRepository movieRepository;

    /// add movie
    public void addMovie(String movieName, MultipartFile poster, Long price , String language) throws IOException {

        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setPrice(price);
        movie.setLanguage(language);
        movie.setReleaseData( LocalDate.now());

        // unique file name
        String uniquePosterFileName = UUID.randomUUID() + "-thumbnail-" + poster.getOriginalFilename();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            // compress image byte
            Thumbnails.of(poster.getInputStream())
                    .scale(1.0)
                    .outputQuality(0.75)
                    .toOutputStream(outputStream);
        } catch (Exception e) {
            // TODO: handle exception
        }

        /// convert byte[] into multipart
        MultipartFile compressedThumbnailFile = new CustomMultipartFile(

                outputStream.toByteArray(),
                "file",
                poster.getOriginalFilename(),
                poster.getContentType());

        // minio store
        // String posterThumbnailUrl = s3StorageService.uploadFile(compressedThumbnailFile, uniquePosterFileName);

        // store in db
        movie.setPosterThumbnailUrl( "uuuuuuuuuuuuuuuuuuuuuuuuu");

        movieRepository.save(movie);

    }

    public DisplayMovieResponseDTO displayMovieInfo() {

        DisplayMovieResponseDTO response = new DisplayMovieResponseDTO();

        List<Movie> movieList = movieRepository.findAll();

        for (Movie movie : movieList) {
            response.setName(movie.getName());
            response.setPosterThumbnailUrl(movie.getPosterThumbnailUrl());
            response.setPrice(movie.getPrice());
            response.setName(movie.getName());
            response.setReleaseData(movie.getReleaseData());
        }

        return response;

    }

}
