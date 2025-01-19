package org.cotato.tlinkserver.domain.homework.application;

import org.cotato.tlinkserver.domain.homework.infra.repository.HomeworkRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeworkService {

	private final HomeworkRepository homeworkRepository;

}
