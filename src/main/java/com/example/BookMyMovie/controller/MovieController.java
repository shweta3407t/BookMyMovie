package com.example.BookMyMovie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.BookMyMovie.dto.DisplayMovieResponseDTO;
import com.example.BookMyMovie.service.MovieService;
import com.example.BookMyMovie.service.S3StorageService;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/movie")
  @CrossOrigin(origins = "*")

public class MovieController {

    @Autowired
    public MovieService movieService;

    @Autowired
    public S3StorageService s3StorageService;

    // add movie
    @PostMapping ("/add")
    // ( value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addMovie(@RequestParam("movieName") String movieName,
            @ModelAttribute("poster") MultipartFile poster,
            @RequestParam("price") Long price,
            @RequestParam("language") String language) throws IOException {

                System.out.println("sssssssssssssssssssssssss");
        movieService.addMovie(movieName, poster, price, language);
        return ResponseEntity.ok("Movie added successfully");
    }

    // display movie info
    @GetMapping("/displayMovieInfo")
    public ResponseEntity<DisplayMovieResponseDTO> displayMovieInfo(@RequestParam String thumbnailUrl) {

        DisplayMovieResponseDTO response = movieService.displayMovieInfo();

        return ResponseEntity.ok(response);

    }

    // display posterThumbnailImage
    @GetMapping("/posterThumbnail")
    public ResponseEntity<Resource> showMovieThumbnailImage(@RequestParam String thumbnailUrl) throws IOException {

        // get image from mimio
        ResponseBytes<GetObjectResponse> imageFile = s3StorageService.displayMoviePoster(thumbnailUrl);
        byte[] data = imageFile.asByteArray();
        ByteArrayResource resource = new ByteArrayResource(data);

        // get content type
        String contentType = imageFile.response().contentType();
        MediaType mediaType = contentType != null
                ? MediaType.parseMediaType(contentType)
                : MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\" " + resource.getFilename() + "\"")
                .body(resource);
    }

}
