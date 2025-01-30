package org.cotato.tlinkserver.api.facade;

import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RegistrationService;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomResponse;
import org.cotato.tlinkserver.domain.room.application.dto.response.RoomsResponse;
import org.cotato.tlinkserver.domain.user.User;
import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomFacade {

	private final RoomService roomService;
	private final RegistrationService registrationService;

	public RoomsResponse getRoomsAndOpponents(Long userId) {
		List<Tuple> roomsAndNames = registrationService.getRooms(userId);
		List<User> opponents = roomsAndNames.stream()
			.map(entry -> registrationService.getOpponent(entry.get(0, Room.class).getId(), userId))
			.toList();

		int size = roomsAndNames.size();
		List<RoomResponse> rooms = new ArrayList<>();

		for (int i=0; i<size; i++) {
			RoomResponse room = RoomResponse.from(
				roomsAndNames.get(i).get(0, Room.class),
				roomsAndNames.get(i).get(1, String.class),
				opponents.get(i)
			);

			rooms.add(room);
		}

		return RoomsResponse.from(rooms);
	}

}
