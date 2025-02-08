package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.api.facade.CounselingLogFacade;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.request.CounselingLogSaveRequest;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogDetailResponse;
import org.cotato.tlinkserver.domain.counselingLog.application.dto.response.CounselingLogsResponse;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<BaseResponse<?>> getCounselingLogs(@PathVariable(value = "roomId") Long roomId) {
		CounselingLogsResponse counselingLogs = counselingLogFacade.getCounselingLogs(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, counselingLogs);
	}

	@Permission(role = {Role.TEACHER})
	@PostMapping
	public ResponseEntity<BaseResponse<?>> saveCounselingLog(@PathVariable(value = "roomId") @IdValidation Long roomId,
		@RequestBody @Validated CounselingLogSaveRequest counselingLogSaveRequest) {
		counselingLogFacade.saveCounselingLog(roomId, counselingLogSaveRequest);
		return ApiResponseUtil.success(SuccessMessage.CREATED);
	}

	@GetMapping("/{counselingLogId}")
	public ResponseEntity<BaseResponse<?>> getCounselingLog(@PathVariable(value = "counselingLogId") Long counselingLogId) {
		CounselingLogDetailResponse counselingLogDetail = counselingLogFacade.getCounselingLog(counselingLogId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, counselingLogDetail);
	}

	@PutMapping("/{counselingLogId}")
	public ResponseEntity<BaseResponse<?>> modifyCounselingLog(@PathVariable(value = "counselingLogId") Long counselingLogId,
		@RequestBody CounselingLogSaveRequest counselingLogSaveRequest) {
		counselingLogFacade.modifyCounselingLog(counselingLogId, counselingLogSaveRequest);
		return ApiResponseUtil.success(SuccessMessage.MODIFIED);
	}

	@DeleteMapping("/{counselingLogId}")
	public ResponseEntity<BaseResponse<?>> removeCounselingLog(@PathVariable(value = "counselingLogId") Long counselingLogId) {
		counselingLogFacade.removeCounselingLog(counselingLogId);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

}
