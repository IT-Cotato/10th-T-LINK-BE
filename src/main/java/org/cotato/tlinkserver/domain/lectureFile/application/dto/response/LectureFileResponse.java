package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

public record LectureFileResponse(
	Long lectureFileId,
	String originalName,
	String fileUrl
)
{
	public static LectureFileResponse from(final Long id, final String originalName, final String fileUrl) {
		return new LectureFileResponse(id, originalName, fileUrl);
	}
}
