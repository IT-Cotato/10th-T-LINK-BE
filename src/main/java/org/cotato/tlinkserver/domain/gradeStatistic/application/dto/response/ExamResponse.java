package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.gradeStatistic.Exam;

@Builder
public record ExamResponse(
        Long examId,
        String examName,
        Integer grade
) {
    public static ExamResponse from(Exam exam) {
        return ExamResponse.builder()
                .examId(exam.getId())
                .examName(exam.getName())
                .grade(exam.getGrade())
                .build();
    }
}
