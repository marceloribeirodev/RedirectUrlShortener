package com.marceloribeirodev.redirectUrlShortner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.Map;

public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final S3Client s3Client = S3Client.builder().build();

    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        String pathParameters = input.get("rawPath").toString();
        String shortUrlCode = pathParameters.replace("/", "");

        if((shortUrlCode == null) || shortUrlCode.isEmpty()){
            throw new IllegalArgumentException("Invalid input: 'shortUrlCode' is required");
        }

        // Criar uma request do tipo GET para o S3
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("url-shortener-storage-lambda-marceloribeirodev")
                .key(shortUrlCode + ".json")
                .build();

        InputStream s3ObjectStream;

        try{
            s3ObjectStream = s3Client.getObject(getObjectRequest);
        }catch (Exception exception){
            throw new RuntimeException("Error fetching data from S3: " + exception.getMessage(), exception);
        }

        return null;
    }
}