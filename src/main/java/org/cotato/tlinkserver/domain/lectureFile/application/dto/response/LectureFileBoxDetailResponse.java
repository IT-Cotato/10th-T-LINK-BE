package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;

public record LectureFileBoxDetailResponse
        (
                Long lectureFileBoxId,
                String lectureFileBoxName,
                String updatedAt,
                List<LectureFileResponse> lectureFiles
        ) {
    public static LectureFileBoxDetailResponse from(final LectureFileBox lectureFileBox,
                                                    final List<LectureFileResponse> lectureFiles) {
        return new LectureFileBoxDetailResponse(
                lectureFileBox.getId(),
                lectureFileBox.getName(),
                lectureFileBox.getUpdatedAt().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                lectureFiles
        );
    }
}
