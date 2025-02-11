package org.cotato.tlinkserver.api.facade.dto.response;

import java.util.List;
import org.cotato.tlinkserver.domain.room.Registration;

public record RoomInfoDetailDTO(
        String roomName,
        String subject,
        Integer depositAt,
        List<RoomInfoDetailLessonDayDTO> lessonDays
) {
    public static RoomInfoDetailDTO from(final Registration registration) {
        Integer deposit = null;
        if (registration.isDeposit()) {
            deposit = registration.getRoom().getDepositAt();
        }

        return new RoomInfoDetailDTO(
                registration.getRoomName(),
                registration.getRoom().getSubject(),
                deposit,
                registration.getRoom().getLessonDays().stream()
                        .map(RoomInfoDetailLessonDayDTO::from)
                        .toList()
        );
    }
}
