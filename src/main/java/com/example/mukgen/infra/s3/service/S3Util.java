package com.example.mukgen.infra.s3.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Util {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile){


        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();

        try {
            objectMetadata.setContentLength(multipartFile.getInputStream().available());
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        try{
            amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objectMetadata);
        } catch (Exception e){
            throw new IllegalStateException();
        }


        return amazonS3.getUrl(bucket, s3FileName).toString();

    }

    public void deleteFile(String fileName) throws IOException{
        try {
            amazonS3.deleteObject(bucket, fileName);
        } catch (SdkClientException e){
            throw new IOException("삭제를 실패하였습니다.", e);
        }
    }
}
