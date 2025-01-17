package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;

public record LectureFileBoxesResponse(
	List<LectureFileBoxResponse> lectureFileBoxDTOs
) {
	public static LectureFileBoxesResponse from(final List<LectureFileBox> lectureFileBoxes) {
		return new LectureFileBoxesResponse(
			lectureFileBoxes.stream()
				.map(LectureFileBoxResponse::from)
				.toList()
		);
	}
}
