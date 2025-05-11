package org.cotato.tlinkserver.api.facade.dto.response;

import java.util.List;
import org.cotato.tlinkserver.domain.room.Registration;

public record RoomDetailDTO(
        long roomId,
        String roomName,
        String studentName,
        String subject,
        List<RoomDetailLessonDayDTO> lessonDays,
        Integer depositAt,
        RoomDetailPermissionDTO permission,
        String shareCode
) {
    public static RoomDetailDTO of(final Registration userRegistration, final String studentUsername) {
        Integer depositAt = null;

        if (userRegistration.getDepositPermission()) {
            depositAt = userRegistration.getRoom().getDepositAt();
        }

        return new RoomDetailDTO(
                userRegistration.getId(),
                userRegistration.getRoomName(),
                studentUsername,
                userRegistration.getRoom().getSubject(),
                userRegistration.getRoom().getLessonDays().stream()
                        .map(RoomDetailLessonDayDTO::from)
                        .toList(),
                depositAt,
                RoomDetailPermissionDTO.from(userRegistration),
                userRegistration.getRoom().getShareCode()
        );
    }
}
