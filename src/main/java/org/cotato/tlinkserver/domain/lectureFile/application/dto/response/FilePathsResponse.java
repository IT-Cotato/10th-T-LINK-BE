package org.cotato.tlinkserver.domain.lectureFile.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public record FilePathsResponse(
	List<String> filePaths
)
{
	public static FilePathsResponse from(final List<String> savedFilePaths) {
		List<String> filePaths = new ArrayList<>(savedFilePaths);
		return new FilePathsResponse(filePaths);
	}
}
