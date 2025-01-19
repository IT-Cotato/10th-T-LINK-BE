package org.cotato.tlinkserver.domain.homework.application.dto.response;

public record HomeworkFileResponse
	(
		Long id,
		String originalName,
		String fileUrl
	)
{
	public static HomeworkFileResponse from(final Long id, final String originalName, final String fileUrl) {
		return new HomeworkFileResponse(id, originalName, fileUrl);
	}
}
