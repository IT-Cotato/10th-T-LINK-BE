package org.cotato.tlinkserver.api.dto;

import java.util.List;
import lombok.Builder;
import org.cotato.tlinkserver.api.dto.response.RoomLessonDayResponse;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.dto.response.OpponentResponse;
import org.cotato.tlinkserver.domain.user.User;

@Builder
public record RoomJoinResponse
        (
                Long roomId,
                String roomName,
                String subject,
                List<RoomLessonDayResponse> lessonDays,
                OpponentResponse opponent
        ) {
    public static RoomJoinResponse from(Registration registration) {
        Room room = registration.getRoom();
        User opponent = registration.getUser();

        return RoomJoinResponse.builder()
                .roomId(room.getId())
                .roomName(registration.getRoomName())
                .subject(room.getSubject())
                .lessonDays(room.getLessonDays().stream()
                        .map(lessonDay -> RoomLessonDayResponse.from(lessonDay.getLessonDay().getInKorean())).toList())
                .opponent(OpponentResponse.from(opponent, opponent.getUsername()))
                .build();
    }
}
