package org.cotato.tlinkserver.api.facade;

import java.util.List;

import org.cotato.tlinkserver.domain.room.application.RegistrationService;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomModifyRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomDataResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomFacade {

	private final RoomService roomService;
	private final RegistrationService registrationService;

	public RoomsResponse getRooms(final Long userId) {
		List<RoomDataResponse> roomData = registrationService.getRooms(userId);
		List<RoomResponse> rooms = roomData.stream()
			.map(r -> RoomResponse.from(r.room(), r.roomName(), r.user()))
			.toList();

		return RoomsResponse.from(rooms);
	}

	public RoomModifyResponse getRoomModify(final Long userId, final Long roomId) {
		return registrationService.getRoomModify(userId, roomId);
	}

	public void modifyRoom(final Long userId, final Long roomId, final RoomModifyRequest roomModifyRequest) {
		if (registrationService.getRooms(userId).stream().anyMatch(r -> r.room().getId().equals(roomId)))
			registrationService.modifyRoom(userId, roomId, roomModifyRequest);
	}

	public void deleteRoom(final Long userId, final Long roomId) {
		if (registrationService.getRooms(userId).stream().anyMatch(r -> r.room().getId().equals(roomId)))
			roomService.deleteRoom(roomId);
	}

}
