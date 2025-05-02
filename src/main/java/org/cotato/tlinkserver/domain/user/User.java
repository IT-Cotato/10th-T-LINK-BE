package org.cotato.tlinkserver.domain.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cotato.tlinkserver.auth.command.OnboardCommand;
import org.cotato.tlinkserver.domain.homework.HomeworkFile;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class User {

    private static final String DEFAULT_STATUS_MESSAGE = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SocialProvider provider;

    @Column(length = 100)
    private String socialId;

    @Column(length = 10)
    private String username;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 250)
    private String profileUrl;

    @Column(nullable = false, length = 50)
    private String statusMessage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HomeworkFile> homeworkFiles = new ArrayList<>();

    @Builder
    public User(String socialId, SocialProvider provider, String username, String phoneNumber, String profileUrl, Role role,
                Gender gender) {
        this.socialId = socialId;
        this.provider = provider;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.profileUrl = profileUrl;
        this.role = role;
        this.gender = gender;
        this.statusMessage = DEFAULT_STATUS_MESSAGE;
    }

    public static User create(AuthUser createAuthUser) {
        return User.builder()
                .socialId(createAuthUser.getSocialId())
                .provider(createAuthUser.getSocialProvider())
                .profileUrl(createAuthUser.getSocialProfileUrl())
                .role(createAuthUser.getRole())
                .build();
    }

    public void addOnboardInfo(OnboardCommand command) {
        this.role = command.role();
        this.username = command.username();
        this.phoneNumber = command.phoneNumber();
        this.gender = command.gender();
    }

    public void addHomeworkFile(HomeworkFile homeworkFile) {
        homeworkFiles.add(homeworkFile);
        homeworkFile.setUser(this);
    }

    public void addRegistration(Registration registration) {
        registrations.add(registration);
        registration.setUser(this);
    }

    public boolean isOnboarding() {
        return role == Role.ONBOARDING;
    }
}
