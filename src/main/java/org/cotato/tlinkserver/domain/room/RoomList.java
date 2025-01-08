package org.cotato.tlinkserver.domain.room;

import org.cotato.tlinkserver.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "room_lists")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class RoomList {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Column(name = "room_name", nullable = false, length = 50)
	private String name;

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
	public RoomList(User user, Room room, String name, boolean lectureFile, boolean homework, boolean gradeStatistic,
		boolean counselingLog, boolean deposit) {
		this.user = user;
		this.room = room;
		this.name = name;
		this.lectureFile = lectureFile;
		this.homework = homework;
		this.gradeStatistic = gradeStatistic;
		this.counselingLog = counselingLog;
		this.deposit = deposit;
	}
}
