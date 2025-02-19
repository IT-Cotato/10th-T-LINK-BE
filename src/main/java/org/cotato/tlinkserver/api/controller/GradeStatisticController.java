package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.api.facade.GradeStatisticFacade;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamBoxRequest;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamRequest;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamBoxesResponse;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamsResponse;
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

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getExamBoxes(@PathVariable("roomId") @IdValidation Long roomId) {
        ExamBoxesResponse examBoxes = gradeStatisticFacade.getExamBoxes(roomId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, examBoxes);
    }

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @DeleteMapping("/{examBoxId}")
    public ResponseEntity<BaseResponse<?>> removeExamBox(@PathVariable("roomId") @IdValidation Long roomId,
                                                         @PathVariable("examBoxId") @IdValidation Long examBoxId) {
        gradeStatisticFacade.removeExamBox(roomId, examBoxId);
        return ApiResponseUtil.success(SuccessMessage.DELETED);
    }

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @PostMapping("/{examBoxId}")
    public ResponseEntity<BaseResponse<?>> saveExam(@RequestBody @Validated SaveExamRequest saveExamRequest,
                                                    @PathVariable("examBoxId") @IdValidation Long examBoxId) {
        gradeStatisticFacade.saveExam(saveExamRequest, examBoxId);
        return ApiResponseUtil.success(SuccessMessage.CREATED);
    }

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @GetMapping("/{examBoxId}")
    public ResponseEntity<BaseResponse<?>> getExams(@PathVariable("examBoxId") @IdValidation Long examBoxId) {
        ExamsResponse exams = gradeStatisticFacade.getExams(examBoxId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, exams);
    }

    @Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
    @DeleteMapping("/{examBoxId}/exams/{examId}")
    public ResponseEntity<BaseResponse<?>> removeExam(@PathVariable("examBoxId") @IdValidation Long examBoxId,
                                                         @PathVariable("examId") @IdValidation Long examId) {
        gradeStatisticFacade.removeExam(examBoxId, examId);
        return ApiResponseUtil.success(SuccessMessage.DELETED);
    }

}
