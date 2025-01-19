package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.HomeworkFacade;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/homeworks")
@RequiredArgsConstructor
public class HomeworkController {

	private final HomeworkFacade homeworkFacade;

	@GetMapping
	public ResponseEntity<DataResponse<HomeworksResponse>> getHomeworks(@PathVariable(value = "roomId") Long roomId) {
		HomeworksResponse homeworks = homeworkFacade.getHomeworks(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), homeworks));
	}

}
