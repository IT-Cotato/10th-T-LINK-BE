package org.cotato.tlinkserver.domain.lectureFile.application;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
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

	public LectureFileBox getLectureFileBox(final Long id) {
		return lectureFileBoxRepository.findById(id).orElseThrow();
	}

	public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
		List<LectureFileBox> lectureFileBoxes = lectureFileBoxRepository.findLectureFileBoxesByRoomId(roomId);
		return LectureFileBoxesResponse.from(lectureFileBoxes);
	}

	public void saveLectureFileBox(final LectureFileBox lectureFileBox) {
		lectureFileBoxRepository.save(lectureFileBox);
	}

	public void removeLectureFileBox(final Long id) {
		lectureFileBoxRepository.deleteById(id);
	}

}
