package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;

public record LectureFileResponse(
	Long id,
	String originalName,
	String filePath)
{
	public static LectureFileResponse from(final LectureFile lectureFile) {
		return new LectureFileResponse(
			lectureFile.getId(),
			lectureFile.getOriginalName(),
			lectureFile.getFilePath()
		);
	}
}
