package org.cotato.tlinkserver.domain.room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.cotato.tlinkserver.domain.homework.Homework;
import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.cotato.tlinkserver.domain.user.Bank;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Long id;

	@Column(name = "room_name", nullable = false, length = 50)
	private String name;

	@Column(name = "subject", nullable = false, length = 30)
	private String subject;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "lesson_day", nullable = false, length = 10)
	private DayOfWeek lessonDay;

	@OneToOne
	@JoinColumn(name = "account_bank_id")
	private Bank bank;

	@Column(name = "account_number", nullable = false, length = 20)
	private String accountNumber;

	@Column(name = "deposit_at", nullable = false)
	private int depositAt;

	@Column(name = "deposit_amount", nullable = false)
	private int depositAmount;

	@Embedded
	private RoomPermission roomPermission;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LectureFileBox> lectureFileBoxes = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Homework> homeworks = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CounselingLog> counselingLogs = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomList> roomLists = new ArrayList<>();

	@Builder
	public Room(String name, String subject, DayOfWeek lessonDay, Bank bank,
		String accountNumber, int depositAt, int depositAmount) {
		this.name = name;
		this.subject = subject;
		this.lessonDay = lessonDay;
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

	public void addRoomList(RoomList roomList) {
		roomLists.add(roomList);
		roomList.setRoom(this);
	}

}
