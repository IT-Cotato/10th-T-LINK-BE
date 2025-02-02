package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.facade.BankFacade;
import org.cotato.tlinkserver.domain.bank.application.dto.response.BanksResponse;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/banks")
@RequiredArgsConstructor
public class BankController {

	private final BankFacade bankFacade;

	@GetMapping
	public ResponseEntity<BaseResponse<?>> getBanks() {
		BanksResponse banks = bankFacade.getBanks();
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, banks);
	}

}
