package org.cotato.tlinkserver.domain.room.application;

import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.infra.repository.RoomRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomService {

	private final RoomRepository roomRepository;

	public Room getRoom(final Long id) {
		return roomRepository.findById(id).orElseThrow();
	}

	public void deleteRoom(final Long roomId) {
		roomRepository.deleteById(roomId);
	}

}
