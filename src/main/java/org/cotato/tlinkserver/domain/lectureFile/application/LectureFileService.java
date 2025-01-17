package org.cotato.tlinkserver.domain.lectureFile.application;

import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureFileService {

	private final LectureFileRepository lectureFileRepository;

}
