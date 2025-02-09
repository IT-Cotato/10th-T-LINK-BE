package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkModifyResponse
	(
		Long homeworkId,
		String homeworkName,
		String deadline,
		List<HomeworkFileResponse> homeworkFiles
	)
{
	public static HomeworkModifyResponse from(final Homework homework, final List<HomeworkFileResponse> homeworkFiles) {
		return new HomeworkModifyResponse(
			homework.getId(),
			homework.getName(),
			homework.getDeadline().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
			homeworkFiles
		);
	}
}
