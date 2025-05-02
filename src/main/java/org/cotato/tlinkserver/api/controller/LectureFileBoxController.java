package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.api.facade.LectureFileBoxFacade;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.FileUrlsResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxDetailResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/lectureFileBoxes")
@RequiredArgsConstructor
public class LectureFileBoxController {

    private final LectureFileBoxFacade lectureFileBoxFacade;

    @Permission(role = {Role.TEACHER, Role.PARENT, Role.STUDENT})
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getLectureFileBoxes(
            @PathVariable(value = "roomId") @IdValidation Long roomId) {
        LectureFileBoxesResponse lectureFileBoxes = lectureFileBoxFacade.getLectureFileBoxes(roomId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, lectureFileBoxes);
    }

    @Permission(role = {Role.TEACHER})
    @PostMapping
    public ResponseEntity<BaseResponse<?>> saveLectureFileBox(@PathVariable(value = "roomId") @IdValidation Long roomId,
                                                              @RequestParam("lectureFileBoxName") String lectureFileBoxName,
                                                              @RequestPart(value = "lectureFiles") List<MultipartFile> lectureFiles)
            throws IOException {
        lectureFileBoxFacade.saveLectureFileBox(roomId, lectureFileBoxName, lectureFiles);
        return ApiResponseUtil.success(SuccessMessage.CREATED);
    }

    @Permission(role = {Role.TEACHER, Role.PARENT, Role.STUDENT})
    @GetMapping("/{lectureFileBoxId}")
    public ResponseEntity<BaseResponse<?>> getLectureFileBox(
            @PathVariable(value = "lectureFileBoxId") @IdValidation Long lectureFileBoxId) {
        LectureFileBoxDetailResponse lectureFileBox = lectureFileBoxFacade.getLectureFileBox(lectureFileBoxId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, lectureFileBox);
    }

    @Permission(role = {Role.TEACHER})
    @DeleteMapping("/{lectureFileBoxId}")
    public ResponseEntity<BaseResponse<?>> removeLectureFileBox(
            @PathVariable(value = "lectureFileBoxId") @IdValidation Long lectureFileBoxId) {
        lectureFileBoxFacade.removeLectureFileBox(lectureFileBoxId);
        return ApiResponseUtil.success(SuccessMessage.DELETED);
    }

    @Permission(role = {Role.TEACHER})
    @PatchMapping("/{lectureFileBoxId}")
    public ResponseEntity<BaseResponse<?>> modifyLectureFileBox(
            @PathVariable(value = "lectureFileBoxId") @IdValidation Long lectureFileBoxId,
            @RequestParam("lectureFileBoxName") String lectureFileBoxName,
            @RequestPart(value = "addLectureFiles", required = false) List<MultipartFile> addLectureFiles,
            @RequestParam(value = "removeLectureFiles", required = false) List<Long> removeLectureFiles)
            throws IOException {
        lectureFileBoxFacade.modifyLectureFileBox(lectureFileBoxId, lectureFileBoxName, addLectureFiles,
                removeLectureFiles);
        return ApiResponseUtil.success(SuccessMessage.MODIFIED);
    }

    @Permission(role = {Role.STUDENT, Role.TEACHER, Role.PARENT})
    @GetMapping("/{lectureFileBoxId}/download")
    public ResponseEntity<BaseResponse<?>> getLectureFileUrls(
            @PathVariable(value = "lectureFileBoxId") @IdValidation Long lectureFileBoxId) {
        FileUrlsResponse fileUrls = lectureFileBoxFacade.getFilePaths(lectureFileBoxId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, fileUrls);
    }

}
