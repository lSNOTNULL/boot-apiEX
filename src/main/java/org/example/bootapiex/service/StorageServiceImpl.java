package org.example.bootapiex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
public class StroageServiceImpl implements StroageService {
    @Value("${supabase.url}")
    private String url;
    @Value("${supabase.access-key}")
    private String accessKey;
    @Value("${supabase.bucket-name}")
    private String bucketName;


    @Override
    public String upload(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String extension = file.getContentType().split("/")[1];//파일 포맷. (이미지일 경우)
        // TODO : 이상한 파일을 넣어보자
        String boundary = "Boundary-%s".formatted(uuid);
        String filename = "%s.%s".formatted(uuid, extension);
        //Java 내장 HttpRequest 사용 주의
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/storage/v1/object/%s/%s".formatted(url,bucketName,filename)))
                //  URL 주소는 bucket 넣는 api 활용한것
                .header("Authorization", "Bearer %s".formatted(accessKey))
                .header("Content-Type", "multipart/form-data; boundary=%s".formatted(boundary))
                .POST(ofMimeMulitpartData(file, boundary))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) {
            throw new RuntimeException("Response code is not 200");
        }
        return filename;

        return "";
    }
}
