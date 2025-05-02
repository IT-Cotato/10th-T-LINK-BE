package org.cotato.tlinkserver.domain.room.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.user.User;

@Builder
public record RoomDataResponse
        (
                Room room,
                String roomName,
                User user
        ) {
    public static RoomDataResponse from(Registration registration) {
        return RoomDataResponse.builder()
                .room(registration.getRoom())
                .roomName(registration.getRoomName())
                .user(registration.getUser())
                .build();
    }
}
