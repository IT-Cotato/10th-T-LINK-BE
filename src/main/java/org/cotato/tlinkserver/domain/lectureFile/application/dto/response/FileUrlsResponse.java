package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public record FileUrlsResponse(
        List<String> fileUrls
) {
    public static FileUrlsResponse from(final List<String> savedFilePaths) {
        List<String> filePaths = new ArrayList<>(savedFilePaths);
        return new FileUrlsResponse(filePaths);
    }
}
