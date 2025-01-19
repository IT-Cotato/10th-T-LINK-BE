package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.time.LocalDateTime;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkResponse(
	Long id,
	String createdAt,
	String name,
	String deadline,
	boolean passed
)
{
	public static HomeworkResponse from(Homework homework) {
		return new HomeworkResponse(
			homework.getId(),
			homework.getCreatedAt().toString(),
			homework.getName(),
			homework.getDeadline().toString(),
			homework.getDeadline().isAfter(LocalDateTime.now())
		);
	}
}
