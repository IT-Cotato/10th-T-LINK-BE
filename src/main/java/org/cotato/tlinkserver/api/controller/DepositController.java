package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.api.facade.DepositFacade;
import org.cotato.tlinkserver.domain.bank.application.dto.request.DepositRequest;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositModifyResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositResponse;
import org.cotato.tlinkserver.domain.user.constant.Role;
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

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/deposit")
@RequiredArgsConstructor
public class DepositController {

    private final DepositFacade depositFacade;

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getDeposit(@PathVariable(value = "roomId") @IdValidation Long roomId) {
        DepositResponse deposit = depositFacade.getDeposit(roomId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, deposit);
    }

    @Permission(role = {Role.TEACHER})
    @GetMapping("/modify")
    public ResponseEntity<BaseResponse<?>> getDepositModify(@PathVariable(value = "roomId") @IdValidation Long roomId) {
        DepositModifyResponse depositModify = depositFacade.getDepositModify(roomId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, depositModify);
    }

    @Permission(role = {Role.TEACHER})
    @PutMapping("/modify")
    public ResponseEntity<BaseResponse<?>> modifyDeposit(@PathVariable(value = "roomId") @IdValidation Long roomId,
                                                         @RequestBody DepositRequest depositRequest) {
        depositFacade.modifyDeposit(roomId, depositRequest);
        return ApiResponseUtil.success(SuccessMessage.MODIFIED);
    }

}
