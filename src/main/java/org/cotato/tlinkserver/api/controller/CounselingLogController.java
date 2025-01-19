package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.CounselingLogFacade;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.request.CounselingLogSaveRequest;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogDetailResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<DataResponse<?>> saveCounselingLog(@PathVariable(value = "roomId") Long roomId,
		@RequestBody CounselingLogSaveRequest counselingLogSaveRequest) {
		counselingLogFacade.saveCounselingLog(roomId, counselingLogSaveRequest);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.CREATED, SuccessMessage.CREATED.getDetailMessage(), null));
	}

	@GetMapping("/{counselingLogId}")
	public ResponseEntity<DataResponse<CounselingLogDetailResponse>> getCounselingLog(@PathVariable(value = "counselingLogId") Long counselingLogId) {
		CounselingLogDetailResponse counselingLogDetail = counselingLogFacade.getCounselingLog(counselingLogId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), counselingLogDetail));
	}

	@PutMapping("/{counselingLogId}")
	public ResponseEntity<DataResponse<?>> modifyCounselingLog(@PathVariable(value = "counselingLogId") Long counselingLogId,
		@RequestBody CounselingLogSaveRequest counselingLogSaveRequest) {
		counselingLogFacade.modifyCounselingLog(counselingLogId, counselingLogSaveRequest);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.MODIFIED.getDetailMessage(), null));
	}

}
