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
                registration.isLectureFile(),
                registration.isHomework(),
                registration.isGradeStatistic(),
                registration.isCounselingLog(),
                registration.isDeposit()
        );
    }
}
