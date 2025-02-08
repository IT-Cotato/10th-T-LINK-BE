package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.util.List;

public record LectureFileBoxDetailResponse
	(
		Long lectureFileBoxId,
		String lectureFileBoxName,
		List<LectureFileResponse> lectureFiles
	)
{
	public static LectureFileBoxDetailResponse from(final Long lectureFileBoxId, final String lectureFileBoxName, final List<LectureFileResponse> lectureFiles) {
		return new LectureFileBoxDetailResponse(lectureFileBoxId, lectureFileBoxName, lectureFiles);
	}
}
