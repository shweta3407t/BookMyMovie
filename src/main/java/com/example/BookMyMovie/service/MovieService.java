package com.example.BookMyMovie.service;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BookMyMovie.models.Movie;
import com.example.BookMyMovie.repository.MovieRepository;

import net.coobird.thumbnailator.Thumbnails;
    import org.springframework.mock.web.MockMultipartFile;
 

@Service
public class MovieService {

    @Autowired 
    public  S3StorageService s3StorageService;
    
    @Autowired
    public MovieRepository movieRepository;

    public void addMovie(String movieName, MultipartFile poster , Long price ) {

        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setPrice(price);

 
        //unique file name 
        String uniquePosterFileName = UUID.randomUUID() + "-thumbnail-" + poster.getOriginalFilename();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

         try {
 

             Thumbnails.of(poster.getInputStream())
         .scale(1.0)
         .outputQuality(0.75)
         .toOutputStream( outputStream);
         } catch (Exception e) {
            // TODO: handle exception
         }


         //convert byte[] into  multipart
         MultipartFile c =new MockMultipa
        //  MultipartFile compressedThumbnailFile =new MockMultipartFile(
        //     "file" ,
        //     uniquePosterFileName ,
        //     poster.getContentType() , 
        //     outputStream.toByteArray()
        //  ) ;

         //minio store 
        String posterThumbnailUrl = s3StorageService.uploadFile(compressedThumbnailFile , uniquePosterFileName);
 
         //store in db
        movie.setPosterThumbnailUrl(posterThumbnailUrl);

 
    }

}
