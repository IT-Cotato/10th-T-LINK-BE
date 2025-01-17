package org.cotato.tlinkserver.api.facade;

import java.io.IOException;
import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileBoxService;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileService;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileBoxRepository;
import org.cotato.tlinkserver.domain.lectureFile.infra.repository.LectureFileRepository;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LectureFileBoxFacade {

	private final LectureFileBoxService lectureFileBoxService;
	private final LectureFileService lectureFileService;
	private final RoomService roomService;
	private final S3FileHandler s3FileHandler;

	public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
		return lectureFileBoxService.getLectureFileBoxes(roomId);
	}

	public void saveLectureFileBox(final Long roomId, final String lectureFileBoxName, final List<MultipartFile> lectureFiles) throws
		IOException {
		Room room = roomService.getRoom(roomId);
		LectureFileBox lectureFileBox = LectureFileBox.builder()
							.room(room)
							.name(lectureFileBoxName)
							.build();

		List<String> filePaths = lectureFiles.stream()
			.map(lectureFile -> lectureFileService.generateFilePath(lectureFile.getOriginalFilename()))
			.toList();

		int size = lectureFiles.size();

		for (int i=0; i<size; i++) {
			LectureFile lectureFile = LectureFile.builder().	// 강의 자료 파일 생성
				lectureFileBox(lectureFileBox).
				filePath(filePaths.get(i)).
				originalName(lectureFiles.get(i).getOriginalFilename())
				.build();

			s3FileHandler.uploadFile(lectureFiles.get(i), filePaths.get(i));	// 파일 업로드
			lectureFileBox.addLectureFile(lectureFile);	// 연관 관계 매핑
			lectureFileService.saveLectureFile(lectureFile);	// 강의 자료 파일 저장
		}

		lectureFileBoxService.saveLectureFileBox(lectureFileBox);
	}

	public void removeLectureFileBox(Long lectureFileBoxId) {
		lectureFileBoxService.removeLectureFileBox(lectureFileBoxId);
	}

}
