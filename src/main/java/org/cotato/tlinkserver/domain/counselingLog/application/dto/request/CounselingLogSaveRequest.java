package org.cotato.tlinkserver.domain.counselingLog.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.counselingLog.constant.Engagement;

public record CounselingLogSaveRequest
        (
                @NotBlank
                String title,
                @NotBlank
                String content,
                @NotNull
                Engagement engagement,
                Boolean homeworkSubmitted
        ) {
    public static CounselingLog toCounselingLog(final CounselingLogSaveRequest counselingLogSaveRequest) {
        return CounselingLog.builder()
                .title(counselingLogSaveRequest.title)
                .content(counselingLogSaveRequest.content)
                .engagement(counselingLogSaveRequest.engagement)
                .homeworkSubmitted(counselingLogSaveRequest.homeworkSubmitted)
                .build();
    }
}
