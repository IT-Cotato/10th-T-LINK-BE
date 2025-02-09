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
    public static RoomDetailDTO from(final Registration registration) {
        Integer depositAt = null;

        if (registration.isDeposit()) {
            depositAt = registration.getRoom().getDepositAt();
        }

        return new RoomDetailDTO(
                registration.getId(),
                registration.getRoomName(),
                registration.getRoom().getStudentName(),
                registration.getRoom().getSubject(),
                registration.getRoom().getLessonDays().stream()
                        .map(RoomDetailLessonDayDTO::from)
                        .toList(),
                depositAt,
                RoomDetailPermissionDTO.from(registration),
                registration.getRoom().getShareCode()
        );
    }
}
