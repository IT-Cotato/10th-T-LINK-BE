package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.CounselingLogFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/counselingLogs")
@RequiredArgsConstructor
public class CounselingLogController {

	private final CounselingLogFacade counselingLogFacade;

}
