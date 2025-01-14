package org.cotato.tlinkserver.domain.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.cotato.tlinkserver.domain.homework.HomeworkFile;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User {

	@Id
	@Column(name = "user_id", updatable = false)
	private Long id;

	@Column(name = "username", nullable = false, length = 10)
	private String username;

	@Column(name = "phone_number", nullable = false, unique = true, length = 15)
	private String phoneNumber;

	@Column(name = "profile_path", nullable = false, length = 250)
	private String profilePath;

	@Column(name = "background_color", nullable = false, length = 10)
	private String backgroundColor;

	@Column(name = "status_message", length = 50)
	private String statusMessage;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 10)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, length = 2)
	private Gender gender;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Registration> registrations = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<HomeworkFile> homeworkFiles = new ArrayList<>();

	@Builder
	public User(String username, String phoneNumber, String profilePath, String backgroundColor, Role role) {
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.profilePath = profilePath;
		this.backgroundColor = backgroundColor;
		this.statusMessage = "";
		this.role = role;
	}

	// 연관 관계 메서드
	public void addRoomList(Registration registration) {
		registrations.add(registration);
		registration.setUser(this);
	}

	public void addHomeworkFile(HomeworkFile homeworkFile) {
		homeworkFiles.add(homeworkFile);
		homeworkFile.setUser(this);
	}

}
