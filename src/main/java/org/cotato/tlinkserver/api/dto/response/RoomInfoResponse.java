package org.cotato.tlinkserver.api.dto.response;

import java.util.List;
import org.cotato.tlinkserver.api.facade.dto.response.RoomInfoDTO;

public record RoomInfoResponse(
        List<RoomInfoDetailResponse> roomInfo
) {
    public static RoomInfoResponse from(RoomInfoDTO roomInfoDTO) {
        return new RoomInfoResponse(
                roomInfoDTO.roomInfo().stream()
                        .map(RoomInfoDetailResponse::from)
                        .toList()
        );
    }
}
