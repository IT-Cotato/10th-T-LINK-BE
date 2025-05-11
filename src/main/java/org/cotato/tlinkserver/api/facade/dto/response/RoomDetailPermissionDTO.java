package org.cotato.tlinkserver.api.facade.dto.response;

import org.cotato.tlinkserver.domain.room.Registration;

public record RoomDetailPermissionDTO(
        boolean lectureFiles,
        boolean homework,
        boolean gradeStatistic,
        boolean counselingLog,
        boolean deposit
) {
    public static RoomDetailPermissionDTO from(final Registration registration) {
        return new RoomDetailPermissionDTO(
                registration.getLectureFilePermission(),
                registration.getHomeworkPermission(),
                registration.getGradeStatisticPermission(),
                registration.getCounselingLogPermission(),
                registration.getDepositPermission()
        );
    }
}
