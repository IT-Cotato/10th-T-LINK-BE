package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;
import lombok.Builder;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

@Builder
public record RoomModifyResponse
        (
                Long roomId,
                String roomName,
                String studentName,
                String subject,
                List<String> lessonDays,
                StudentPermissionResponse studentPermission,
                ParentPermissionResponse parentPermission
        ) {
    public static RoomModifyResponse from(Room room, Long userId) {
        Registration registration = room.getRegistration(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_REGISTRATION));
        return new RoomModifyResponse(room.getId(), registration.getRoomName(), room.getStudentName(),
                room.getSubject(),
                room.getLessonDays().stream().map(lessonDay -> lessonDay.getLessonDay().getInKorean()).toList(),
                StudentPermissionResponse.from(room.getStudentPermission()),
                ParentPermissionResponse.from(room.getParentPermission())
        );
    }
}
