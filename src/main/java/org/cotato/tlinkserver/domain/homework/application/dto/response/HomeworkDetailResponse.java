package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkDetailResponse
	(
		Long id,
		String name,
		List<HomeworkFileResponse> teacherFiles,
		List<HomeworkFileResponse> studentFiles
	)
{
	public static HomeworkDetailResponse from(final Homework homework, final List<HomeworkFileResponse> teacherFiles,
		final List<HomeworkFileResponse> studentFiles) {
		return new HomeworkDetailResponse(
			homework.getId(),
			homework.getName(),
			teacherFiles,
			studentFiles
		);
	}
}