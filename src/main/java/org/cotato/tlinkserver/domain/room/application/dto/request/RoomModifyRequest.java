package org.cotato.tlinkserver.domain.room.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.cotato.tlinkserver.api.facade.dto.request.RoomLessonDayDTO;

public record RoomModifyRequest(
        @NotBlank
        String roomName,
        @NotBlank
        String studentName,
        @NotBlank
        String subject,
        @NotNull
        List<RoomLessonDayDTO> lessonDays,
        @NotNull
        ParentPermissionRequest parentPermission,
        @NotNull
        StudentPermissionRequest studentPermission
) {
}
