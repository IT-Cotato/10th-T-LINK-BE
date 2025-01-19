package org.cotato.tlinkserver.api.facade;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.homework.HomeworkFile;
import org.cotato.tlinkserver.domain.homework.application.HomeworkFileService;
import org.cotato.tlinkserver.domain.homework.application.HomeworkService;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.domain.homework.infra.repository.HomeworkRepository;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HomeworkFacade {

	private final HomeworkService homeworkService;
	private final HomeworkFileService homeworkFileService;
	private final RoomService roomService;
	private final S3FileHandler s3FileHandler;

	@Transactional(readOnly = true)
	public HomeworksResponse getHomeworks(final Long roomId) {
		return homeworkService.getHomeworks(roomId);
	}

	@Transactional
	public void saveHomework(final Long roomId, final String homeworkName, final String deadline, final List<MultipartFile> homeworks) throws
		IOException {
		Room room = roomService.getRoom(roomId);
		Homework homework = Homework.builder()
			.room(room)
			.name(homeworkName)
			.deadline(LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
			.build();

		room.addHomework(homework);	// 과외 방과 숙제 간 연관 관계 매핑
		this.saveHomeworkFiles(homeworks, homework);
		homeworkService.saveHomework(homework);
	}

	@Transactional
	public void removeHomework(final Long homeworkId) {
		Homework homework = homeworkService.getHomework(homeworkId);
		List<HomeworkFile> homeworkFiles = homework.getHomeworkFiles();

		homeworkFiles.forEach(homeworkFile -> s3FileHandler.deleteFile(homeworkFile.getS3Key()));
		homework.getRoom().getHomeworks().remove(homework);
	}

	private void saveHomeworkFiles(final List<MultipartFile> homeworkFiles, final Homework homework) throws
		IOException {
		// S3 파일 저장 경로 생성
		List<String> filePaths = homeworkFiles.stream()
			.map(homeworkFile -> s3FileHandler.generateS3Key(homeworkFile.getOriginalFilename()))
			.toList();

		int size = homeworkFiles.size();

		for (int i=0; i<size; i++) {
			HomeworkFile homeworkFile = HomeworkFile.builder().	// 강의 자료 파일 생성
				s3Key(filePaths.get(i)).
				originalName(homeworkFiles.get(i).getOriginalFilename())
				.build();

			s3FileHandler.uploadFile(homeworkFiles.get(i), filePaths.get(i));	// 파일 업로드
			homework.addHomeworkFile(homeworkFile);	// 숙제와 숙제 파일 간 연관 관계 매핑
			homeworkService.saveHomework(homework);	// 강의 자료 파일 저장
		}
	}

}
