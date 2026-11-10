package com.example.BookMyMovie.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.EncryptionTypeMismatchException;
import software.amazon.awssdk.services.s3.model.InvalidRequestException;
import software.amazon.awssdk.services.s3.model.InvalidWriteOffsetException;
 import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.TooManyPartsException;

@Service 
public class S3StorageService {

     private  String endpoint;
     private final String bucketName;
     private final S3Client s3Client;

     public S3StorageService(S3Client s3Client ,
        @Value  ("${minio.endpoint}") String endpoint ,
        @Value  ("${minio.bucket-name}") String bucketName 

     ){
        this.bucketName=bucketName;
        this.s3Client=s3Client;
        this.endpoint=endpoint;
     }


 

    public  String uploadFile( MultipartFile file , String  uniqueOriginalFileName ) throws InvalidRequestException, InvalidWriteOffsetException, TooManyPartsException, EncryptionTypeMismatchException, S3Exception, AwsServiceException, SdkClientException, IOException{

         PutObjectRequest putObjectAclRequest = PutObjectRequest.builder()
         .bucket( bucketName)
         .key( uniqueOriginalFileName)
         .contentType(file.getContentType())
         .build();

         s3Client.putObject(putObjectAclRequest ,  RequestBody.fromBytes(file.getBytes()) );

        return  endpoint+ "/" + bucketName + "/" + uniqueOriginalFileName;
    }

}
