package org.cotato.tlinkserver.global.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileHandler {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucket;

    private final String EXAMPLE_DIR = "example/";

    public List<String> examples(final List<MultipartFile> multipartFiles, final Long userId) throws IOException {
        List<String> exampleUploadUrls = new ArrayList<>();

        multipartFiles.forEach(mf -> {
            try {
                exampleUploadUrls.add(example(mf, userId));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return exampleUploadUrls;
    }

    public String example(final MultipartFile multipartFile, final Long userId) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] MultipartFile -> File convert fail"));

        return upload(uploadFile, EXAMPLE_DIR, userId);
    }

    // S3 파일 삭제
    public void deleteFile(final String fileUrl) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileUrl));
    }

    public void deleteFiles(final List<String> fileUrls) {
        fileUrls.forEach(this::deleteFile);
    }

    // S3로 파일 업로드
    private String upload(final File uploadFile, final String dirName, final Long userId) {
        String fileName = dirName + userId + "/" + UUID.randomUUID() + uploadFile.getName();

        String uploadFileUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);

        return uploadFileUrl;
    }

    // S3로 업로드
    private String putS3(final File uploadFile, final String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
                CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(final File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }

        log.info("File delete fail: " + targetFile.getPath());
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(final MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.home") + "/" + file.getOriginalFilename();

        File convertFile = new File(filePath);
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
