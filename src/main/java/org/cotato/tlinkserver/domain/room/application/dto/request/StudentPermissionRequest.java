package org.cotato.tlinkserver.domain.room.application.dto.request;

import org.cotato.tlinkserver.domain.studentPermission.StudentPermission;

public record StudentPermissionRequest(
        boolean lectureFile,
        boolean homework,
        boolean gradeStatistic,
        boolean counselingLog,
        boolean deposit
) {
    public StudentPermission toEntity() {
        return new StudentPermission(lectureFile, homework, gradeStatistic, counselingLog, deposit);
    }
}
