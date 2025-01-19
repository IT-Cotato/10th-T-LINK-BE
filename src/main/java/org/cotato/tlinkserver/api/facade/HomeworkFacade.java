package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.domain.homework.application.HomeworkFileService;
import org.cotato.tlinkserver.domain.homework.application.HomeworkService;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HomeworkFacade {

	private final HomeworkService homeworkService;
	private final HomeworkFileService homeworkFileService;
	private final S3FileHandler s3FileHandler;

	@Transactional(readOnly = true)
	public HomeworksResponse getHomeworks(final Long roomId) {
		return homeworkService.getHomeworks(roomId);
	}

}
