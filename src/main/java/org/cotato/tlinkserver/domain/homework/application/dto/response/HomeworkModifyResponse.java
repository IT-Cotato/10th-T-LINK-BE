package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkModifyResponse
	(
		Long id,
		String name,
		List<HomeworkFileResponse> homeworkFiles
	)
{
	public static HomeworkModifyResponse from(final Homework homework, final List<HomeworkFileResponse> homeworkFiles) {
		return new HomeworkModifyResponse(
			homework.getId(),
			homework.getName(),
			homeworkFiles
		);
	}
}
