package org.cotato.tlinkserver.domain.counselingLog.application.dto.response;

import java.time.format.DateTimeFormatter;
import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;

public record CounselingLogResponse
        (
                Long id,
                String title,
                String updatedAt
        ) {
    public static CounselingLogResponse from(final CounselingLog counselingLog) {
        return new CounselingLogResponse(
                counselingLog.getId(),
                counselingLog.getTitle(),
                counselingLog.getUpdatedAt().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        );
    }
}
