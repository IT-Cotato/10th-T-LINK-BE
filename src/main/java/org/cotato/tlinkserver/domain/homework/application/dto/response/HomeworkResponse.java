package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.cotato.tlinkserver.domain.homework.Homework;

public record HomeworkResponse(
	Long homeworkId,
	String homeworkName,
	String createdAt,
	String deadline,
	boolean passed
)
{
	public static HomeworkResponse from(final Homework homework) {
		return new HomeworkResponse(
			homework.getId(),
			homework.getName(),
			homework.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
			homework.getDeadline().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
			LocalDate.now().isAfter(homework.getDeadline())
		);
	}
}
