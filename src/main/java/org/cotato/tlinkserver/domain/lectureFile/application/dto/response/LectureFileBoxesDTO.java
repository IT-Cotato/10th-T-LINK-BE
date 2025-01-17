package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;

public record LectureFileBoxesDTO(
	List<LectureFileBoxDTO> lectureFileBoxDTOs
) {
	public static LectureFileBoxesDTO from(final List<LectureFileBox> lectureFileBoxes) {
		return new LectureFileBoxesDTO(
			lectureFileBoxes.stream()
				.map(LectureFileBoxDTO::from)
				.toList()
		);
	}
}
