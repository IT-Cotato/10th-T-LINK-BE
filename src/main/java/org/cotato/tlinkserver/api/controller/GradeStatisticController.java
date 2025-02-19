package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.api.facade.GradeStatisticFacade;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamBoxRequest;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/gradeStatistics")
@RequiredArgsConstructor
public class GradeStatisticController {

    private final GradeStatisticFacade gradeStatisticFacade;

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @PostMapping
    public ResponseEntity<BaseResponse<?>> saveExamBox(@RequestBody @Validated SaveExamBoxRequest saveExamBoxRequest,
        @PathVariable("roomId") @IdValidation Long roomId) {
        gradeStatisticFacade.saveExamBox(saveExamBoxRequest, roomId);
        return ApiResponseUtil.success(SuccessMessage.CREATED);
    }

}
