package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;

public record LectureFileBoxDetailResponse(
	Long id,
	String name,
	List<LectureFileResponse> lectureFiles
	)
{
	public static LectureFileBoxDetailResponse from(final LectureFileBox lectureFileBox) {
		return new LectureFileBoxDetailResponse(
			lectureFileBox.getId(),
			lectureFileBox.getName(),
			lectureFileBox.getLectureFiles().stream().map(LectureFileResponse::from).toList()
		);
	}
}
