package org.cotato.tlinkserver.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import org.cotato.tlinkserver.domain.room.RoomList;
import org.hibernate.annotations.CreationTimestamp;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
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

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 10)
	private Role role;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomList> roomLists;

	@Builder
	public User(String username, String phoneNumber, String profilePath, Role role) {
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.profilePath = profilePath;
		this.role = role;
	}

	// 연관 관계 메서드
	public void addRoomList(RoomList roomList) {
		roomLists.add(roomList);
		roomList.setUser(this);
	}

}
