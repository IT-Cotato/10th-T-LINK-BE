package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;

import org.cotato.tlinkserver.annotation.IdValidation;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.api.facade.HomeworkFacade;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworkDetailResponse;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworkModifyResponse;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/homeworks")
@RequiredArgsConstructor
public class HomeworkController {

	private final HomeworkFacade homeworkFacade;

	@GetMapping
	public ResponseEntity<BaseResponse<?>> getHomework(@PathVariable(value = "roomId") Long roomId) {
		HomeworksResponse homeworks = homeworkFacade.getHomeworks(roomId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, homeworks);
	}

	@Permission(role = {Role.TEACHER})
	@PostMapping
	public ResponseEntity<BaseResponse<?>> saveHomeworks(@PathVariable(value = "roomId") @IdValidation Long roomId,
		@UserId Long userId,
		@RequestParam("homeworkName") String homeworkName,
		@RequestParam("deadline") String deadline,
		@RequestPart(value = "homeworkFiles") List<MultipartFile> homeworkFiles) throws IOException {
		homeworkFacade.saveHomework(userId, roomId, homeworkName, deadline, homeworkFiles);
		return ApiResponseUtil.success(SuccessMessage.CREATED);
	}

	@Permission(role = {Role.TEACHER, Role.STUDENT, Role.PARENT})
	@GetMapping("/{homeworkId}")
	public ResponseEntity<BaseResponse<?>> getHomeworkDetail(@PathVariable(value = "homeworkId") @IdValidation Long homeworkId) {
		HomeworkDetailResponse homeworkDetail = homeworkFacade.getHomework(homeworkId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, homeworkDetail);
	}

	@DeleteMapping("/{homeworkId}")
	public ResponseEntity<BaseResponse<?>> deleteHomework(@PathVariable(value = "homeworkId") Long homeworkId) {
		homeworkFacade.removeHomework(homeworkId);
		return ApiResponseUtil.success(SuccessMessage.DELETED);
	}

	@PatchMapping("/{homeworkId}")
	public ResponseEntity<BaseResponse<?>> modifyHomework(@PathVariable(value = "homeworkId") Long homeworkId,
		@UserId Long userId,
		@RequestParam("homeworkName") String homeworkName,
		@RequestParam("deadline") String deadline,
		@RequestParam("removeHomeworkFiles") List<Long> removeHomeworkFiles,
		@RequestPart(value = "addHomeworkFiles") List<MultipartFile> addHomeworkFiles) throws IOException {
		homeworkFacade.modifyHomework(userId, homeworkId, homeworkName, deadline, removeHomeworkFiles, addHomeworkFiles);
		return ApiResponseUtil.success(SuccessMessage.MODIFIED);
	}

	@Permission(role = {Role.TEACHER, Role.STUDENT})
	@GetMapping("/{homeworkId}/info")
	public ResponseEntity<BaseResponse<?>> getHomeworkModify(@PathVariable(value = "homeworkId") @IdValidation Long homeworkId) {
		HomeworkModifyResponse homeworkModifys = homeworkFacade.getHomeworkModify(homeworkId);
		return ApiResponseUtil.success(SuccessMessage.SUCCESS, homeworkModifys);
	}

}
