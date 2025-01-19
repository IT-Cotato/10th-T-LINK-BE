package org.cotato.tlinkserver.domain.counselingLog.application.dto;

import org.cotato.tlinkserver.domain.counselingLog.infra.repository.CounselingLogRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselingLogService {

	private final CounselingLogRepository counselingLogRepository;

}
