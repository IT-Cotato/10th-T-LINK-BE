package org.cotato.tlinkserver.global.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

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

    // S3 파일 업로드
    public void upload(final MultipartFile multipartFile, final String key) throws IOException {
        try (InputStream is = multipartFile.getInputStream()) {
			s3Operations.upload(bucket, key, is,
                ObjectMetadata.builder().contentType(multipartFile.getContentType()).build());
        }
    }

    // S3 파일 다운로드
    public S3Resource download(final String key) throws NoSuchKeyException {
        return s3Operations.download(bucket, key);
    }

    // S3 파일 삭제
    public void deleteFile(final String key) {
        s3Operations.deleteObject(bucket, key);
    }

    public void deleteFiles(final List<String> keys) {
        keys.forEach(this::deleteFile);
    }

}
