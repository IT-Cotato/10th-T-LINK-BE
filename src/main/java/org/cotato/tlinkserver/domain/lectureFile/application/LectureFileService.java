package org.cotato.tlinkserver.domain.lectureFile.application;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureFileService {

    private final LectureFileRepository lectureFileRepository;

    public List<String> getKeys(final Long lectureFileBoxId) {
        List<String> keys = lectureFileRepository.findS3KeysByLectureFileBoxId(lectureFileBoxId);
        return new ArrayList<>(keys);
    }

}
