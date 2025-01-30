package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.RoomFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomFacade roomFacade;

}
