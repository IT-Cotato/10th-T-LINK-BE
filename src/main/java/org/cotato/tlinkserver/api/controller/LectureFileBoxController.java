package org.cotato.tlinkserver.api.controller;

import java.io.IOException;
import java.util.List;

import org.cotato.tlinkserver.api.dto.response.DataResponse;
import org.cotato.tlinkserver.api.facade.LectureFileBoxFacade;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.global.util.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/rooms/{roomId}/lectureFileBoxes")
@RequiredArgsConstructor
public class LectureFileBoxController {

	private final LectureFileBoxFacade lectureFileBoxFacade;

	@GetMapping
	public ResponseEntity<DataResponse<LectureFileBoxesResponse>> getLectureFileBoxes(@PathVariable(value = "roomId") Long roomId) {
		LectureFileBoxesResponse lectureFileBoxes = lectureFileBoxFacade.getLectureFileBoxes(roomId);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.OK, SuccessMessage.SUCCESS.getDetailMessage(), lectureFileBoxes));
	}

	@PostMapping
	public ResponseEntity<DataResponse<?>> saveLectureFileBox(@PathVariable(value = "roomId") Long roomId,
		@RequestParam("lectureFileBoxName") String lectureFileBoxName,
		@RequestPart(value = "lectureFiles") List<MultipartFile> lectureFiles) throws IOException {
		lectureFileBoxFacade.saveLectureFileBox(roomId, lectureFileBoxName, lectureFiles);
		return ResponseEntity.ok(DataResponse.of(HttpStatus.CREATED, SuccessMessage.CREATED.getDetailMessage(), null));
	}

}
