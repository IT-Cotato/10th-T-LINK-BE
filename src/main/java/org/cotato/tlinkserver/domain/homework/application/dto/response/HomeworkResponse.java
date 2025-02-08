package org.cotato.tlinkserver.domain.homework.application.dto.response;

import java.time.LocalDate;

import org.cotato.tlinkserver.domain.homework.Homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HomeworkResponse(
	@NotNull
	Long id,
	@NotBlank
	String createdAt,
	@NotBlank
	String name,
	@NotBlank
	String deadline,
	boolean passed
)
{
	public static HomeworkResponse from(final Homework homework) {
		return new HomeworkResponse(
			homework.getId(),
			homework.getCreatedAt().toString(),
			homework.getName(),
			homework.getDeadline().toString(),
			homework.getDeadline().isAfter(LocalDate.now())
		);
	}
}
