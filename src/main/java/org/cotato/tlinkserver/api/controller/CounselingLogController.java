package org.cotato.tlinkserver.api.controller;

import java.util.List;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.CounselingLogFacade;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/counselingLogs")
@RequiredArgsConstructor
public class CounselingLogController {

	private final CounselingLogFacade counselingLogFacade;

	@GetMapping
	public ResponseEntity<DataResponse<CounselingLogsResponse>> getCounselingLogs(@PathVariable(value = "roomId") Long roomId) {
		CounselingLogsResponse counselingLogs = counselingLogFacade.getCounselingLogs(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), counselingLogs));
	}

}
