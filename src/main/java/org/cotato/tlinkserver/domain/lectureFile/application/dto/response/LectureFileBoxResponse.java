package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.time.format.DateTimeFormatter;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;

public record LectureFileBoxResponse(
	Long id,
	String name,
	String updateAt)
{
	public static LectureFileBoxResponse from(final LectureFileBox lectureFileBox) {
		return new LectureFileBoxResponse(
			lectureFileBox.getId(),
			lectureFileBox.getName(),
			lectureFileBox.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
		);
	}
}
