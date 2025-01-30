package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomFacade {

	private final RoomService roomService;

}
