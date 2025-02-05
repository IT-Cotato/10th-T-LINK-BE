package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;

import org.cotato.tlinkserver.api.facade.LectureFileBoxFacade;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileBoxService;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.FilePathsResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxDetailResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/lectureFileBoxes")
@RequiredArgsConstructor
public class LectureFileBoxController {

	private final LectureFileBoxFacade lectureFileBoxFacade;
	private final LectureFileBoxService lectureFileBoxService;

	@GetMapping
	public ResponseEntity<BaseResponse<?>> getLectureFileBoxes(@PathVariable(value = "roomId") Long roomId) {
		LectureFileBoxesResponse lectureFileBoxes = lectureFileBoxFacade.getLectureFileBoxes(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, lectureFileBoxes);
	}

	@PostMapping
	public ResponseEntity<BaseResponse<?>> saveLectureFileBox(@PathVariable(value = "roomId") Long roomId,
		@RequestParam("lectureFileBoxName") String lectureFileBoxName,
		@RequestPart(value = "lectureFiles") List<MultipartFile> lectureFiles) throws IOException {
		lectureFileBoxFacade.saveLectureFileBox(roomId, lectureFileBoxName, lectureFiles);
		return ApiResponseUtil.success(SuccessMessage.CREATED);
	}

	@GetMapping("/{lectureFileBoxId}")
	public ResponseEntity<BaseResponse<?>> getLectureFileBox(@PathVariable(value = "lectureFileBoxId") Long lectureFileBoxId) {
		LectureFileBoxDetailResponse lectureFileBox = lectureFileBoxFacade.getLectureFileBox(lectureFileBoxId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, lectureFileBox);
	}

	@DeleteMapping("/{lectureFileBoxId}")
	public ResponseEntity<BaseResponse<?>> removeLectureFileBox(@PathVariable(value = "lectureFileBoxId") Long lectureFileBoxId) {
		lectureFileBoxFacade.removeLectureFileBox(lectureFileBoxId);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

	@PatchMapping("/{lectureFileBoxId}")
	public ResponseEntity<BaseResponse<?>> modifyLectureFileBox(@PathVariable(value = "lectureFileBoxId") Long lectureFileBoxId,
		@RequestParam("lectureFileBoxName") String lectureFileBoxName,
		@RequestPart(value = "addLectureFiles") List<MultipartFile> addLectureFiles,
		@RequestParam("removeLectureFiles") List<Long> removeLectureFiles) throws IOException {
		lectureFileBoxFacade.modifyLectureFileBox(lectureFileBoxId, lectureFileBoxName, addLectureFiles, removeLectureFiles);
		return ApiResponseUtil.success(SuccessMessage.MODIFIED);
	}

	@GetMapping("/{lectureFileBoxId}/download")
	public ResponseEntity<BaseResponse<?>> getLectureFiles(@PathVariable(value = "lectureFileBoxId") Long lectureFileBoxId) {
		FilePathsResponse filePaths = lectureFileBoxFacade.getFilePaths(lectureFileBoxId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, filePaths);
	}

}
