package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DepositFacade {

	private final RoomService roomService;

	public DepositResponse getDeposit(final Long roomId) {
		Room room = roomService.getRoom(roomId);
		return DepositResponse.from(room);
	}
}
