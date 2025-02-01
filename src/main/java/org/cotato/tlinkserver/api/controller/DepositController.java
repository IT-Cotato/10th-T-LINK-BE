package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.DepositFacade;
import org.cotato.tlinkserver.domain.bank.application.dto.request.DepositRequest;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositModifyResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositResponse;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/deposit")
@RequiredArgsConstructor
public class DepositController {

	private final DepositFacade depositFacade;

	@GetMapping
	public ResponseEntity<BaseResponse<?>> getDeposit(@PathVariable(value = "roomId") Long roomId) {
		DepositResponse deposit = depositFacade.getDeposit(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, deposit);
	}

	@GetMapping("/modify")
	public ResponseEntity<BaseResponse<?>> getDepositModify(@PathVariable(value = "roomId") Long roomId) {
		DepositModifyResponse depositModify = depositFacade.getDepositModify(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, depositModify);
	}

	@PutMapping("/modify")
	public ResponseEntity<BaseResponse<?>> modifyDeposit(@PathVariable(value = "roomId") Long roomId,
		@RequestBody DepositRequest depositRequest) {
		depositFacade.modifyDeposit(roomId, depositRequest);
		return ApiResponseUtil.success(SuccessMessage.MODIFIED);
	}

}
