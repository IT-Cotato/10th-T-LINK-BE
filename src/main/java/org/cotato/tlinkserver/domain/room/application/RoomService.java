package org.cotato.tlinkserver.domain.room.application;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.infra.repository.RoomRepository;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
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
		return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
	}

	public Room getRoom(final String shareCode) {
		return roomRepository.findByShareCode(shareCode).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
	}

	public Long saveRoom(final Room room) {
		Room save = roomRepository.save(room);
		return save.getId();
	}

	public void removeRoom(final Long id) {
		roomRepository.deleteById(id);
	}

	public void deleteRoom(final Long roomId) {
		roomRepository.deleteById(roomId);
	}

	public Long saveRoom(Room room) {
		Room save = roomRepository.save(room);
		return save.getId();
	}
}
