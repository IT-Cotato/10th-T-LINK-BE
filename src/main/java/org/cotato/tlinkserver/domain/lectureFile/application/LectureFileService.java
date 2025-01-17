package org.cotato.tlinkserver.domain.lectureFile.application;

import java.util.UUID;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
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
	private final String DIRECTORY_PATH = "/uploads/";

	public void saveLectureFile(LectureFile lectureFile){
		lectureFileRepository.save(lectureFile);
	}

	public String generateFilePath(String originalFileName) {
		String uuid = UUID.randomUUID().toString();
		return DIRECTORY_PATH + uuid + originalFileName;
	}

}
