package org.cotato.tlinkserver.domain.user.application.dto;

import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

	private final UserRepository userRepository;

	public User findUser(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

}
