package org.cotato.tlinkserver.domain.room.application.dto.response;

import java.util.List;
import lombok.Builder;
import org.cotato.tlinkserver.api.dto.response.RoomLessonDayResponse;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

@Builder
public record RoomResponse
        (
                Long roomId,
                String roomName,
                String subject,
                List<RoomLessonDayResponse> lessonDays,
                OpponentResponse opponent
        ) {
    public static RoomResponse from(Room room, String roomName, Registration registration) {
        if (registration == null) {
            return RoomResponse.builder()
                    .roomId(room.getId())
                    .roomName(roomName)
                    .subject(room.getSubject())
                    .lessonDays(room.getLessonDays().stream()
                            .map(lessonDay -> RoomLessonDayResponse.from(lessonDay.getLessonDay().getInKorean()))
                            .toList())
                    .build();
        }

        User teacher = registration.getUser();
        return RoomResponse.builder()
                .roomId(room.getId())
                .roomName(roomName)
                .subject(room.getSubject())
                .lessonDays(room.getLessonDays().stream()
                        .map(lessonDay -> RoomLessonDayResponse.from(lessonDay.getLessonDay().getInKorean()))
                        .toList())
                .opponent(OpponentResponse.from(teacher, teacher.getUsername()))
                .build();
    }

    public static RoomResponse from(Room room, String roomName, Registration registration, String studentName) {
        if (registration == null) {
            return RoomResponse.builder()
                    .roomId(room.getId())
                    .roomName(roomName)
                    .subject(room.getSubject())
                    .lessonDays(room.getLessonDays().stream()
                            .map(lessonDay -> RoomLessonDayResponse.from(lessonDay.getLessonDay().getInKorean()))
                            .toList())
                    .build();
        }

        User student = registration.getUser();
        return RoomResponse.builder()
                .roomId(room.getId())
                .roomName(roomName)
                .subject(room.getSubject())
                .lessonDays(room.getLessonDays().stream()
                        .map(lessonDay -> RoomLessonDayResponse.from(lessonDay.getLessonDay().getInKorean()))
                        .toList())
                .opponent(OpponentResponse.from(student, studentName))
                .build();
    }
}
