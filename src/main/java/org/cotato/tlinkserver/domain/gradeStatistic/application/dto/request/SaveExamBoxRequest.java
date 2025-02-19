package org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;

public record SaveExamBoxRequest(
        @NotBlank
        String examBoxName
) {
        public ExamBox toEntity() {
                return ExamBox.builder().name(examBoxName).build();
        }
}
