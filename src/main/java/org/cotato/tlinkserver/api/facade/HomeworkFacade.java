package org.cotato.tlinkserver.api.facade;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.homework.HomeworkFile;
import org.cotato.tlinkserver.domain.homework.application.HomeworkService;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworkDetailResponse;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworkFileResponse;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworkModifyResponse;
import org.cotato.tlinkserver.domain.homework.application.dto.response.HomeworksResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.application.UserService;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HomeworkFacade {

	private final HomeworkService homeworkService;
	private final UserService userService;
	private final RoomService roomService;
	private final S3FileHandler s3FileHandler;

	@Transactional(readOnly = true)
	public HomeworksResponse getHomeworks(final Long roomId) {
		List<Homework> homeworks = homeworkService.getHomeworks(roomId);
		return HomeworksResponse.from(homeworks);
	}

	@Transactional
	public void saveHomework(final Long userId, final Long roomId, final String homeworkName, final String deadline, final List<MultipartFile> homeworks) throws
		IOException {
		User user = userService.getValidUser(userId);
		Room room = roomService.getRoom(roomId);
		Homework homework = Homework.builder()
			.room(room)
			.name(homeworkName)
			.deadline(LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
			.build();

		room.addHomework(homework);	// 과외 방과 숙제 간 연관 관계 매핑
		this.saveHomeworkFiles(homeworks, homework, user);
	}

	@Transactional
	public void removeHomework(final Long homeworkId) {
		Homework homework = homeworkService.getHomework(homeworkId);
		List<HomeworkFile> homeworkFiles = homework.getHomeworkFiles();

		homeworkFiles.forEach(homeworkFile -> s3FileHandler.deleteFile(homeworkFile.getS3Key()));
		homework.getRoom().getHomeworks().remove(homework);
	}

	@Transactional
	public void modifyHomework(final Long userId, final Long homeworkId, final String homeworkName, final String deadline, final List<Long> removeHomeworkFiles,
		final List<MultipartFile> addHomeworkFiles) throws IOException {
		User user = userService.getValidUser(userId);
		Homework homework = homeworkService.getHomework(homeworkId);
		List<HomeworkFile> homeworkFiles = homework.getHomeworkFiles();

		removeHomeworkFiles.forEach(id -> {
			HomeworkFile homeworkFile = homeworkFiles.stream()
				.filter(file -> file.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));

			s3FileHandler.deleteFile(homeworkFile.getS3Key());
			homeworkFiles.remove(homeworkFile);
		});

		this.saveHomeworkFiles(addHomeworkFiles, homework, user);

		if (user.getRole().equals(Role.TEACHER))
			homework.setName(homeworkName);
			homework.setDeadline(LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

	@Transactional(readOnly = true)
	public HomeworkDetailResponse getHomework(final Long homeworkId) {
		Homework homework = homeworkService.getHomework(homeworkId);
		List<HomeworkFile> homeworkFiles = homework.getHomeworkFiles();

		List<HomeworkFileResponse> teacherFiles = homeworkFiles.stream()
			.filter(file -> file.getUser().getRole().equals(Role.TEACHER))
			.toList().stream().map(teacherFile -> {
				try {
					return HomeworkFileResponse.from(
						teacherFile.getId(),
						teacherFile.getOriginalName(),
						s3FileHandler.downloadFile(teacherFile.getS3Key()).getURL().toString()
					);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}).toList();

		List<HomeworkFileResponse> studentFiles = homeworkFiles.stream()
			.filter(file -> file.getUser().getRole().equals(Role.STUDENT))
			.toList().stream().map(studentFile -> {
				try {
					return HomeworkFileResponse.from(
						studentFile.getId(),
						studentFile.getOriginalName(),
						s3FileHandler.downloadFile(studentFile.getS3Key()).getURL().toString()
					);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}).toList();

		return HomeworkDetailResponse.from(homework, teacherFiles, studentFiles);
	}

	@Transactional(readOnly = true)
	public HomeworkModifyResponse getHomeworkModify(final Long homeworkId) {
		Homework homework = homeworkService.getHomework(homeworkId);
		List<HomeworkFile> homeworkFiles = homework.getHomeworkFiles();
		List<HomeworkFileResponse> homeworkFileModifys = homeworkFiles.stream().map(file -> {
			try {
				return HomeworkFileResponse.from(
					file.getId(),
					file.getOriginalName(),
					s3FileHandler.downloadFile(file.getS3Key()).getURL().toString());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).toList();

		return HomeworkModifyResponse.from(homework, homeworkFileModifys);
	}

	private void saveHomeworkFiles(final List<MultipartFile> homeworkFiles, final Homework homework, User user) throws
		IOException {
		// S3 파일 저장 경로 생성
		List<String> filePaths = homeworkFiles.stream()
			.map(homeworkFile -> s3FileHandler.generateS3Key(homeworkFile.getOriginalFilename()))
			.toList();

		int size = homeworkFiles.size();

		for (int i=0; i<size; i++) {
			HomeworkFile homeworkFile = HomeworkFile.builder().	// 숙제 파일 생성
				s3Key(filePaths.get(i)).
				originalName(homeworkFiles.get(i).getOriginalFilename())
				.build();

			s3FileHandler.uploadFile(homeworkFiles.get(i), filePaths.get(i));	// 숙제 파일 업로드
			homework.addHomeworkFile(homeworkFile);	// 숙제와 숙제 파일 간 연관 관계 매핑
			user.addHomeworkFile(homeworkFile);	// 숙제와 유저 간 연관 관계 매핑
		}
	}

}
