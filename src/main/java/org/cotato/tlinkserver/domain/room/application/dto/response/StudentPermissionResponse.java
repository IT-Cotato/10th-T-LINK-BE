package org.cotato.tlinkserver.domain.room.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.studentPermission.StudentPermission;

@Builder
public record StudentPermissionResponse
        (
                boolean lectureFile,
                boolean homework,
                boolean gradeStatistic,
                boolean counselingLog,
                boolean deposit
        ) {
    public static StudentPermissionResponse from(final StudentPermission studentPermission) {
        return StudentPermissionResponse.builder()
                .lectureFile(studentPermission.isLectureFile())
                .homework(studentPermission.isHomework())
                .gradeStatistic(studentPermission.isGradeStatistic())
                .counselingLog(studentPermission.isCounselingLog())
                .deposit(studentPermission.isDeposit())
                .build();
    }
}
