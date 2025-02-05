package org.cotato.tlinkserver.api.facade;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RegistrationService;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.domain.room.application.dto.request.RoomRequest;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomDataResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomModifyResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.ShareCodeResponse;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.application.dto.UserService;
import org.cotato.tlinkserver.global.util.RandomUtil;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomFacade {

	private final RoomService roomService;
	private final RegistrationService registrationService;
	private final UserService userService;

	public Long saveRoom(final Long userId, final RoomRequest roomRequest) {
		User user = userService.findUser(userId);
		Room room = roomRequest.save(user);
		return roomService.saveRoom(room);
	}

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

	public void modifyRoom(final Long userId, final Long roomId, final RoomRequest roomRequest) {
		if (registrationService.getRooms(userId).stream().anyMatch(r -> r.room().getId().equals(roomId)))
			registrationService.modifyRoom(userId, roomId, roomRequest);
	}

	public void deleteRoom(final Long userId, final Long roomId) {
		if (registrationService.getRooms(userId).stream().anyMatch(r -> r.room().getId().equals(roomId)))
			roomService.deleteRoom(roomId);
	}

	public ShareCodeResponse getShareCode(final Long roomId) {
		String shareCode = RandomUtil.generateRandomCode('0', 'z', 10);
		Room room = roomService.getRoom(roomId);
		room.setShareCode(shareCode);
		return ShareCodeResponse.from(shareCode);
	}

}
