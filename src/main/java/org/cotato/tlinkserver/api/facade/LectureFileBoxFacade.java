package org.cotato.tlinkserver.api.facade;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileBoxService;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileService;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.FilePathsResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxDetailResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LectureFileBoxFacade {

	private final LectureFileBoxService lectureFileBoxService;
	private final LectureFileService lectureFileService;
	private final RoomService roomService;
	private final S3FileHandler s3FileHandler;

	@Transactional(readOnly = true)
	public LectureFileBoxDetailResponse getLectureFileBox(final Long id) {
		LectureFileBox lectureFileBox = lectureFileBoxService.getLectureFileBox(id);
		return LectureFileBoxDetailResponse.from(lectureFileBox);
	}

	@Transactional(readOnly = true)
	public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
		return lectureFileBoxService.getLectureFileBoxes(roomId);
	}

	@Transactional(readOnly = true)
	public FilePathsResponse getFilePaths(final Long lectureFileBoxId) {
		List<String> keys = lectureFileService.getKeys(lectureFileBoxId);
		List<String> urls = keys.stream().map(key -> {
			try {
				return s3FileHandler.downloadFile(key).getURL().toString();
			} catch (IOException e) {
				throw new NoSuchElementException();
			}
		}).toList();

		return FilePathsResponse.from(urls);
	}

	@Transactional
	public void saveLectureFileBox(final Long roomId, final String lectureFileBoxName, final List<MultipartFile> lectureFiles) throws
		IOException {
		Room room = roomService.getRoom(roomId);
		LectureFileBox lectureFileBox = LectureFileBox.builder()
							.room(room)
							.name(lectureFileBoxName)
							.build();

		this.saveLectureFiles(lectureFiles, lectureFileBox);
		lectureFileBoxService.saveLectureFileBox(lectureFileBox);
	}

	@Transactional
	public void removeLectureFileBox(final Long lectureFileBoxId) {
		lectureFileBoxService.removeLectureFileBox(lectureFileBoxId);
	}

	@Transactional
	public void modifyLectureFileBox(final Long lectureFileBoxId, final String lectureFileBoxName,
		List<MultipartFile> addLectureFiles, List<Long> removeLectureFiles) throws IOException {
		LectureFileBox lectureFileBox = lectureFileBoxService.getLectureFileBox(lectureFileBoxId);

		removeLectureFiles.forEach(id ->
			lectureFileBox.getLectureFiles()
				.removeIf(
					lectureFile -> lectureFile.getId().equals(id)
				)
		);

		this.saveLectureFiles(addLectureFiles, lectureFileBox);
		lectureFileBox.setName(lectureFileBoxName);
	}

	public void saveLectureFiles(final List<MultipartFile> lectureFiles, final LectureFileBox lectureFileBox) throws
		IOException {
		// S3 파일 저장 경로 생성
		List<String> filePaths = lectureFiles.stream()
			.map(lectureFile -> lectureFileService.generateKey(lectureFile.getOriginalFilename()))
			.toList();

		int size = lectureFiles.size();

		for (int i=0; i<size; i++) {
			LectureFile lectureFile = LectureFile.builder().	// 강의 자료 파일 생성
				lectureFileBox(lectureFileBox).
				key(filePaths.get(i)).
				originalName(lectureFiles.get(i).getOriginalFilename())
				.build();

			s3FileHandler.uploadFile(lectureFiles.get(i), filePaths.get(i));	// 파일 업로드
			lectureFileBox.addLectureFile(lectureFile);	// 연관 관계 매핑
			lectureFileService.saveLectureFile(lectureFile);	// 강의 자료 파일 저장
		}
	}

}
