package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ExamBoxesResponse(
        List<ExamBoxResponse> examBox
) {
    public static ExamBoxesResponse from(List<ExamBoxResponse> examBox) {
        return ExamBoxesResponse.builder().examBox(examBox).build();
    }
}
