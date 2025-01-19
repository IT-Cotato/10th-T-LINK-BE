package org.cotato.tlinkserver.domain.homework.application.dto.response;

public record HomeworkFileModifyResponse
	(
		Long id,
		String originalName,
		String fileUrl
	)
{
	public static HomeworkFileModifyResponse from(final Long id, final String originalName, final String fileUrl) {
		return new HomeworkFileModifyResponse(id, originalName, fileUrl);
	}
}
