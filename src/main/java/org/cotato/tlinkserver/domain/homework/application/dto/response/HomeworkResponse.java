package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.cotato.tlinkserver.domain.homework.Homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HomeworkResponse(
	@NotNull
	Long homeworkId,
	@NotBlank
	String homeworkName,
	@NotBlank
	String createdAt,
	@NotBlank
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
