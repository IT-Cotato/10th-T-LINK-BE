package org.cotato.tlinkserver.api.facade.dto.response;

import java.util.List;
import org.cotato.tlinkserver.domain.room.Registration;

public record RoomInfoDTO(
        List<RoomInfoDetailDTO> roomInfo
) {
    public static RoomInfoDTO from(final List<Registration> registrations) {
        return new RoomInfoDTO(
                registrations.stream()
                        .map(RoomInfoDetailDTO::from)
                        .toList()
        );
    }
}
