package org.cotato.tlinkserver.domain.room.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.parentPermission.ParentPermission;

@Builder
public record ParentPermissionResponse(
        boolean lectureFile,
        boolean homework,
        boolean gradeStatistic,
        boolean counselingLog,
        boolean deposit
) {
    public static ParentPermissionResponse from(final ParentPermission parentPermission) {
        return ParentPermissionResponse.builder()
                .lectureFile(parentPermission.isLectureFile())
                .homework(parentPermission.isHomework())
                .gradeStatistic(parentPermission.isGradeStatistic())
                .counselingLog(parentPermission.isCounselingLog())
                .deposit(parentPermission.isDeposit())
                .build();
    }
}
