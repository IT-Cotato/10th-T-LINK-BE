package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.DepositFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/deposit")
@RequiredArgsConstructor
public class DepositController {

	private final DepositFacade depositFacade;

}
