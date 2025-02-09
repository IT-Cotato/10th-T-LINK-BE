package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailPermissionDTO;

public record RoomDetailPermissionResponse(
        boolean lectureFiles,
        boolean homework,
        boolean gradeStatistic,
        boolean counselingLog,
        boolean deposit
) {
    public static RoomDetailPermissionResponse from(RoomDetailPermissionDTO permission) {
        return new RoomDetailPermissionResponse(
                permission.lectureFiles(),
                permission.homework(),
                permission.gradeStatistic(),
                permission.counselingLog(),
                permission.deposit()
        );
    }
}
