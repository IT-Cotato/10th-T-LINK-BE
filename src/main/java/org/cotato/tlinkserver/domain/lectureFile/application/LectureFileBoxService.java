package org.cotato.tlinkserver.domain.lectureFile.application;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesDTO;
import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileBoxRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureFileBoxService {

	private final LectureFileBoxRepository lectureFileBoxRepository;

	public LectureFileBoxesDTO getLectureFileBoxes(final Long roomId) {
		List<LectureFileBox> lectureFileBoxes = lectureFileBoxRepository.findLectureFileBoxesByRoomId(roomId);
		return LectureFileBoxesDTO.from(lectureFileBoxes);
	}

}
