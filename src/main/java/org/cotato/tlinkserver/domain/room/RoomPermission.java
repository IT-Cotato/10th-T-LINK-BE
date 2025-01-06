package org.cotato.tlinkserver.domain.room;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {""})
public class RoomPermission {

	@Column(name = "lec_files")
	private boolean lecFiles;

	@Column(name = "homework")
	private boolean homework;

	@Column(name = "grade_statistic")
	private boolean gradeStatistic;

	@Column(name = "counseling_log")
	private boolean counselingLog;

	@Column(name = "account")
	private boolean account;

	@Builder
	public RoomPermission(boolean lecFiles, boolean homework, boolean gradeStatistic, boolean counselingLog,
		boolean account) {
		this.lecFiles = lecFiles;
		this.homework = homework;
		this.gradeStatistic = gradeStatistic;
		this.counselingLog = counselingLog;
		this.account = account;
	}
}
