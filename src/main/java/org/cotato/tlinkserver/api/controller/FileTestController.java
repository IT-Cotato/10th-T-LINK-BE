package org.cotato.tlinkserver.api.controller;

import io.awspring.cloud.s3.S3Resource;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileTestController {

    private final S3FileHandler s3FileHandler;

    @PostMapping("/download")
    public ResponseEntity<?> downloadFile() {
        S3Resource aa = s3FileHandler.downloadFile("aa");
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(aa);
    }

    @DeleteMapping("/delete")
    public void deleteFile() {
        List<String> list = Arrays.asList("aa", "bb");
        s3FileHandler.deleteFiles(list);
    }

    @GetMapping("/url")
    public ResponseEntity<?> getUrls() {
        List<String> list = Arrays.asList("aa", "bb");
        List<URL> fileUrls = s3FileHandler.getFileUrls(list);
        return ResponseEntity.ok().body(fileUrls);
    }

}


