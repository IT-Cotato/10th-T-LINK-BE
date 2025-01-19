package org.cotato.tlinkserver.domain.homework.application;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.domain.homework.infra.repository.HomeworkRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkService {

	private final HomeworkRepository homeworkRepository;

	public HomeworksResponse getHomeworks(final Long roomId) {
		List<Homework> homeworks = homeworkRepository.findHomeworksByRoomId(roomId);
		return HomeworksResponse.from(homeworks);
	}

}
