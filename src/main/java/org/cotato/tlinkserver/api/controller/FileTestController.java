package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileTestController {

    private final S3FileHandler s3FileHandler;

    @PostMapping
    public ResponseEntity<List<String>> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        return ResponseEntity.ok(s3FileHandler.examples(multipartFiles, 1L));
    }

    @DeleteMapping
    public ResponseEntity<List<String>> deleteFiles(@RequestBody List<String> fileUrls) {
        s3FileHandler.deleteFiles(fileUrls);
        return ResponseEntity.ok(fileUrls);
    }
}
