package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileBoxService;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileService;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LectureFileBoxFacade {

	private final LectureFileBoxService lectureFileBoxService;
	private final LectureFileService lectureFileService;

	public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
		return lectureFileBoxService.getLectureFileBoxes(roomId);
	}

}
