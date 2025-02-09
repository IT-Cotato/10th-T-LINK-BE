package org.cotato.tlinkserver.api.dto.response;

import java.util.List;
import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailDTO;
import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailLessonDayDTO;
import org.cotato.tlinkserver.api.facade.dto.response.RoomDetailPermissionDTO;

public record RoomDetailResponse(
        long roomId,
        String roomName,
        String studentName,
        String subject,
        List<RoomDetailLessonDayResponse> lessonDays,
        Integer depositAt,
        RoomDetailPermissionResponse permission,
        String shareCode
) {
    public static RoomDetailResponse from(RoomDetailDTO roomDetailDTO) {
        return new RoomDetailResponse(
                roomDetailDTO.roomId(),
                roomDetailDTO.roomName(),
                roomDetailDTO.studentName(),
                roomDetailDTO.subject(),
                roomDetailDTO.lessonDays().stream()
                        .map(RoomDetailLessonDayResponse::from)
                        .toList(),
                roomDetailDTO.depositAt(),
                RoomDetailPermissionResponse.from(roomDetailDTO.permission()),
                roomDetailDTO.shareCode()
        );
    }
}
