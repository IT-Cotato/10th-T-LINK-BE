package org.cotato.tlinkserver.domain.lectureFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lecture_files")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class LectureFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lecture_file_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_file_box_id")
	private LectureFileBox lectureFileBox;

	@Column(name = "original_name", nullable = false, length = 30)
	private String originalName;

	@Column(name = "file_path", nullable = false, length = 250)
	private String filePath;

	@Builder
	public LectureFile(LectureFileBox lectureFileBox, String originalName, String filePath) {
		this.lectureFileBox = lectureFileBox;
		this.originalName = originalName;
		this.filePath = filePath;
	}
}
