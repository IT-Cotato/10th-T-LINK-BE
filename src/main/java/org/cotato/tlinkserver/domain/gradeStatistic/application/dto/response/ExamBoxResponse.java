package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;

@Builder
public record ExamBoxResponse(
        Long id,
        String name
) {
    public static ExamBoxResponse from(ExamBox examBox) {
        return ExamBoxResponse.builder()
                .id(examBox.getId())
                .name(examBox.getName())
                .build();
    }
}
