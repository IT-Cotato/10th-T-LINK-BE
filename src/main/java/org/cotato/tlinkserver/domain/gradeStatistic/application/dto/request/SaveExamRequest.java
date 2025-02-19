package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cotato.tlinkserver.domain.gradeStatistic.Exam;

public record SaveExamRequest(
        @NotBlank
        String examName,
        @NotNull
        Integer grade
) {
    public Exam toEntity() {
        return Exam.builder()
                .name(examName)
                .grade(grade)
                .build();
    }
}
