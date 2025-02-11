package org.cotato.tlinkserver.api.dto.response;

import java.util.List;
import org.cotato.tlinkserver.api.facade.dto.response.RoomInfoDetailDTO;

public record RoomInfoDetailResponse(
        String roomName,
        String subject,
        Integer depositAt,
        List<RoomInfoDetailLessonDayResponse> lessonDays
) {
    public static RoomInfoDetailResponse from(RoomInfoDetailDTO roomInfoDetailDTO) {
        return new RoomInfoDetailResponse(
                roomInfoDetailDTO.roomName(),
                roomInfoDetailDTO.subject(),
                roomInfoDetailDTO.depositAt(),
                roomInfoDetailDTO.lessonDays().stream()
                        .map(RoomInfoDetailLessonDayResponse::from)
                        .toList()
        );
    }
}
