package org.cotato.tlinkserver.api.controller;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.LectureFileBoxFacade;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesDTO;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/lectureFileBoxes")
@RequiredArgsConstructor
public class LectureFileBoxController {

	private final LectureFileBoxFacade lectureFileBoxFacade;

	@GetMapping
	public ResponseEntity<DataResponse<LectureFileBoxesDTO>> getLectureFileBoxes(@PathVariable(value = "roomId") Long roomId) {
		LectureFileBoxesDTO lectureFileBoxes = lectureFileBoxFacade.getLectureFileBoxes(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), lectureFileBoxes));
	}

}
