package org.cotato.tlinkserver.domain.room;

import org.cotato.tlinkserver.domain.room.constant.DayOfWeek;

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
@Table(name = "lesson_days")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LessonDay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

	@Enumerated(EnumType.STRING)
	@Column(name = "lesson_day", nullable = false, length = 10)
	private DayOfWeek lessonDay;

	@Builder
	public LessonDay(DayOfWeek lessonDay) {
		this.lessonDay = lessonDay;
	}

}
