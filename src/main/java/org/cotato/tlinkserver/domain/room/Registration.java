package org.cotato.tlinkserver.domain.room;

import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registrations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 10)
	private Role role;

	@Column(name = "room_name", nullable = false, length = 50)
	private String roomName;

	@Column(name = "lecture_file")
	private boolean lectureFile;

	@Column(name = "homework")
	private boolean homework;

	@Column(name = "grade_statistic")
	private boolean gradeStatistic;

	@Column(name = "counseling_log")
	private boolean counselingLog;

	@Column(name = "deposit")
	private boolean deposit;

	@Builder
	public Registration(Role role, String roomName, boolean lectureFile, boolean homework, boolean gradeStatistic,
		boolean counselingLog, boolean deposit) {
		this.role = role;
		this.roomName = roomName;
		this.lectureFile = lectureFile;
		this.homework = homework;
		this.gradeStatistic = gradeStatistic;
		this.counselingLog = counselingLog;
		this.deposit = deposit;
	}

}
