package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworksResponse(
	List<HomeworkResponse> homeworks
)
{
	public static HomeworksResponse from(final List<Homework> homeworks) {
		return new HomeworksResponse(
			homeworks.stream()
				.map(HomeworkResponse::from)
				.toList()
		);
	}
}