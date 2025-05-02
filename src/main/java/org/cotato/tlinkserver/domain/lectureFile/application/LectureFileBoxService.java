package org.cotato.tlinkserver.domain.lectureFile.application;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileBoxRepository;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureFileBoxService {

    private final LectureFileBoxRepository lectureFileBoxRepository;

    public LectureFileBox getLectureFileBox(final Long id) {
        return lectureFileBoxRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_LECTURE_FILE_BOX));
    }

    public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
        List<LectureFileBox> lectureFileBoxes = lectureFileBoxRepository.findLectureFileBoxesByRoomId(roomId);
        return LectureFileBoxesResponse.from(lectureFileBoxes);
    }

}
