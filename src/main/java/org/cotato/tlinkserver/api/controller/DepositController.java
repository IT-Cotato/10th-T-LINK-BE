package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.DepositFacade;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositModifyResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxDetailResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/deposit")
@RequiredArgsConstructor
public class DepositController {

	private final DepositFacade depositFacade;

	@GetMapping
	public ResponseEntity<DataResponse<DepositResponse>> getDeposit(@PathVariable(value = "roomId") Long roomId) {
		DepositResponse deposit = depositFacade.getDeposit(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), deposit));
	}

	@GetMapping("/modify")
	public ResponseEntity<DataResponse<DepositModifyResponse>> getDepositModify(@PathVariable(value = "roomId") Long roomId) {
		DepositModifyResponse depositModify = depositFacade.getDepositModify(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), depositModify));
	}

}
