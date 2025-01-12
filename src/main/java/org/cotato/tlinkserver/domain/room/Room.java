package org.cotato.tlinkserver.domain.room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.bank.Bank;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Long id;

	@Column(name = "student_name", nullable = false, length = 20)
	private String studentName;

	@Column(name = "subject", nullable = false, length = 30)
	private String subject;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@OneToOne
	@JoinColumn(name = "account_bank_id")
	private Bank bank;

	@Column(name = "account_number", nullable = false, length = 20)
	private String accountNumber;

	@Column(name = "deposit_at", nullable = false)
	private int depositAt;

	@Column(name = "deposit_amount", nullable = false)
	private int depositAmount;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LectureFileBox> lectureFileBoxes = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Homework> homeworks = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CounselingLog> counselingLogs = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Registration> registrations = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LessonDay> lessonDays = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LessonDay> lessonDays = new ArrayList<>();

	@Builder
	public Room(String subject, Bank bank,
		String accountNumber, int depositAt, int depositAmount) {
		this.subject = subject;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.depositAt = depositAt;
		this.depositAmount = depositAmount;
	}

	// 연관 관계 메서드
	public void addHomework(Homework homework) {
		homeworks.add(homework);
		homework.setRoom(this);
	}

	public void addLectureFileBox(LectureFileBox lectureFileBox) {
		lectureFileBoxes.add(lectureFileBox);
		lectureFileBox.setRoom(this);
	}

	public void addCounselingLog(CounselingLog counselingLog) {
		counselingLogs.add(counselingLog);
		counselingLog.setRoom(this);
	}

	public void addRegistration(Registration registration) {
		registrations.add(registration);
		registration.setRoom(this);
	}

	public void addLessonDay(LessonDay lessonDay) {
		lessonDays.add(lessonDay);
		lessonDay.setRoom(this);
	}

	public void addLessonDay(LessonDay lessonDay) {
		lessonDays.add(lessonDay);
		lessonDay.setRoom(this);
	}

}
