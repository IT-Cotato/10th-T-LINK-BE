package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.HomeworkFacade;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<DataResponse<HomeworksResponse>> getHomework(@PathVariable(value = "roomId") Long roomId) {
		HomeworksResponse homeworks = homeworkFacade.getHomeworks(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), homeworks));
	}

	@PostMapping
	public ResponseEntity<DataResponse<?>> saveHomeworks(@PathVariable(value = "roomId") Long roomId,
		@RequestParam("homeworkName") String homeworkName,
		@RequestParam("deadline") String deadline,
		@RequestPart(value = "homeworkFiles") List<MultipartFile> homeworkFiles) throws IOException {
		homeworkFacade.saveHomework(roomId, homeworkName, deadline, homeworkFiles);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.CREATED, SuccessMessage.CREATED.getDetailMessage(), null));
	}

	@DeleteMapping("/{homeworkId}")
	public ResponseEntity<DataResponse<?>> deleteHomework(@PathVariable(value = "homeworkId") Long homeworkId) {
		homeworkFacade.removeHomework(homeworkId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.DELETED.getDetailMessage(), null));
	}

}
