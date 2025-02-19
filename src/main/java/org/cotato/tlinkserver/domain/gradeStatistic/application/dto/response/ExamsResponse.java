package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ExamsResponse(
        List<ExamResponse> exams
) {
    public static ExamsResponse from(List<ExamResponse> exams) {
        return ExamsResponse.builder().exams(exams).build();
    }
}
