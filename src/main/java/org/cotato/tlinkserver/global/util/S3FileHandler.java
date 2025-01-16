package org.cotato.tlinkserver.global.util;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Operations;
import io.awspring.cloud.s3.S3Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
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

}
