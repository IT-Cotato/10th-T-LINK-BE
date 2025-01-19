package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkModifyResponse
	(
		Long id,
		String name,
		List<HomeworkFileModifyResponse> homeworkFiles
	)
{
	public static HomeworkModifyResponse from(Homework homework, List<HomeworkFileModifyResponse> homeworkFiles) {
		return new HomeworkModifyResponse(
			homework.getId(),
			homework.getName(),
			homeworkFiles
		);
	}
}
