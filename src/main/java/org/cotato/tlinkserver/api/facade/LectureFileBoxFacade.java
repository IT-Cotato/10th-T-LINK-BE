package org.cotato.tlinkserver.api.facade;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileBoxService;
import org.cotato.tlinkserver.domain.lectureFile.application.LectureFileService;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.FileUrlsResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxDetailResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileBoxesResponse;
import org.cotato.tlinkserver.domain.lectureFile.application.dto.response.LectureFileResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class LectureFileBoxFacade {

	private final LectureFileBoxService lectureFileBoxService;
	private final LectureFileService lectureFileService;
	private final RoomService roomService;
	private final S3FileHandler s3FileHandler;
	private final String DIRECTORY_PATH = "lectureFiles/";

	@Transactional(readOnly = true)
	public LectureFileBoxDetailResponse getLectureFileBox(final Long id) {
		LectureFileBox lectureFileBox = lectureFileBoxService.getLectureFileBox(id);
		List<LectureFile> lectureFiles = lectureFileBox.getLectureFiles();

		List<LectureFileResponse> lectureFileResponses = lectureFiles.stream()
			.map(file -> {
				try {
					return LectureFileResponse.from(file.getId(), file.getOriginalName(),
						s3FileHandler.downloadFile(DIRECTORY_PATH + file.getS3Key()).getURL().toString()
					);
				} catch (IOException e) {
					throw new NotFoundException(ErrorMessage.NOT_FOUND);
				}
			}).toList();

		return LectureFileBoxDetailResponse.from(lectureFileBox, lectureFileResponses);
	}

	@Transactional(readOnly = true)
	public LectureFileBoxesResponse getLectureFileBoxes(final Long roomId) {
		return lectureFileBoxService.getLectureFileBoxes(roomId);
	}

	@Transactional(readOnly = true)
	public FileUrlsResponse getFilePaths(final Long lectureFileBoxId) {
		List<String> keys = lectureFileService.getKeys(lectureFileBoxId);
		List<String> urls = keys.stream().map(key -> {
			try {
				return s3FileHandler.downloadFile(DIRECTORY_PATH + key).getURL().toString();
			} catch (IOException e) {
				throw new NotFoundException(ErrorMessage.NOT_FOUND);
			}
		}).toList();

		return FileUrlsResponse.from(urls);
	}

	@Transactional
	public void saveLectureFileBox(final Long roomId, final String lectureFileBoxName, final List<MultipartFile> lectureFiles) throws
		IOException {
		Room room = roomService.getRoom(roomId);
		LectureFileBox lectureFileBox = LectureFileBox.builder()
							.name(lectureFileBoxName)
							.build();

		room.addLectureFileBox(lectureFileBox);
		this.saveLectureFiles(lectureFiles, lectureFileBox);
	}

	@Transactional
	public void removeLectureFileBox(final Long lectureFileBoxId) {
		LectureFileBox lectureFileBox = lectureFileBoxService.getLectureFileBox(lectureFileBoxId);
		List<LectureFile> lectureFiles = lectureFileBox.getLectureFiles();

		lectureFiles.forEach(lectureFile -> s3FileHandler.deleteFile(DIRECTORY_PATH + lectureFile.getS3Key()));
		lectureFileBox.getRoom().getLectureFileBoxes().remove(lectureFileBox);
	}

	@Transactional
	public void modifyLectureFileBox(final Long lectureFileBoxId, final String lectureFileBoxName,
		List<MultipartFile> addLectureFiles, List<Long> removeLectureFiles) throws IOException {
		LectureFileBox lectureFileBox = lectureFileBoxService.getLectureFileBox(lectureFileBoxId);
		List<LectureFile> lectureFiles = lectureFileBox.getLectureFiles();

		removeLectureFiles.forEach(id -> {
			LectureFile lectureFile = lectureFiles.stream()
				.filter(file -> file.getId().equals(id))
				.findFirst()
				.orElseThrow();
			s3FileHandler.deleteFile(DIRECTORY_PATH + lectureFile.getS3Key());
			lectureFiles.remove(lectureFile);
		});

		this.saveLectureFiles(addLectureFiles, lectureFileBox);
		lectureFileBox.setName(lectureFileBoxName);
	}

	private void saveLectureFiles(final List<MultipartFile> lectureFiles, final LectureFileBox lectureFileBox) throws
		IOException {
		// S3 파일 저장 경로 생성
		List<String> filePaths = lectureFiles.stream()
			.map(lectureFile -> s3FileHandler.generateS3Key(lectureFile.getOriginalFilename()))
			.toList();

		int size = lectureFiles.size();

		for (int i=0; i<size; i++) {
			LectureFile lectureFile = LectureFile.builder().	// 강의 자료 파일 생성
				lectureFileBox(lectureFileBox)
				.s3Key(filePaths.get(i))
				.originalName(lectureFiles.get(i).getOriginalFilename())
				.build();

			s3FileHandler.uploadFile(lectureFiles.get(i), DIRECTORY_PATH + filePaths.get(i));	// 파일 업로드
			lectureFileBox.addLectureFile(lectureFile);	// 연관 관계 매핑
		}
	}

}
