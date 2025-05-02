package org.cotato.tlinkserver.domain.room.application.dto.request;

import org.cotato.tlinkserver.domain.parentPermission.ParentPermission;

public record ParentPermissionRequest(
        boolean lectureFile,
        boolean homework,
        boolean gradeStatistic,
        boolean counselingLog,
        boolean deposit
) {
    public ParentPermission toEntity() {
        return new ParentPermission(lectureFile, homework, gradeStatistic, counselingLog, deposit);
    }
}
