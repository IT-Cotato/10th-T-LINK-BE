package org.cotato.tlinkserver.domain.lectureFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.room.Room;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lecture_file_boxes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class LectureFileBox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lecture_file_box_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@Lob
	@Column(name = "description", nullable = false, length = 300)
	private String description;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "lectureFileBox", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LectureFile> lectureFiles = new ArrayList<>();

	@Builder
	public LectureFileBox(Room room, String description) {
		this.room = room;
		this.description = description;
		this.updatedAt = LocalDateTime.now();
	}

	// 연관관계 메서드
	public void addLectureFile(LectureFile lectureFile) {
		lectureFiles.add(lectureFile);
		lectureFile.setLectureFileBox(this);
	}

}
