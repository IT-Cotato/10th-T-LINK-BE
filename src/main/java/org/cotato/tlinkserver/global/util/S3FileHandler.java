package org.cotato.tlinkserver.global.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileHandler {

    private final S3Operations s3Operations;

    @Value("${spring.cloud.aws.s3.bucket-name}")
    private String bucket;
    private final String DIRECTORY_PATH = "/uploads/";
    private final Duration duration = Duration.ofMinutes(10L);  // URL 지속 시간

    // S3 파일 업로드
    public void uploadFile(final MultipartFile multipartFile, final String key) throws IOException {
        try (InputStream is = multipartFile.getInputStream()) {
			s3Operations.upload(bucket, key, is,
                ObjectMetadata.builder().contentType(multipartFile.getContentType()).build());
        }
    }

    public void uploadFiles(final List<MultipartFile> multipartFiles, final List<String> keys) throws IOException {
        int size = keys.size();

        for (int i = 0; i < size; i++){
            uploadFile(multipartFiles.get(i), keys.get(i));
        }
    }

    // S3 파일 다운로드
    public S3Resource downloadFile(final String key) {
        return s3Operations.download(bucket, key);
    }

    // S3 파일 삭제
    public void deleteFile(final String key) {
        s3Operations.deleteObject(bucket, key);
    }

    public void deleteFiles(final List<String> keys) {
        keys.forEach(this::deleteFile);
    }

    // S3 파일 조회 URL 반환
    public URL getFileUrl(final String key) {
        return s3Operations.createSignedGetURL(bucket, key, duration);
    }

    public List<URL> getFileUrls(final List<String> keys) {
        return keys.stream().map(this::getFileUrl).toList();
    }

    // S3 키 생성
    public String generateS3Key(final String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        return DIRECTORY_PATH + uuid + originalFileName;
    }

}
