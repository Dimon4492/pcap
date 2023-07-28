package com.lexx.domain.features.logger

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LogReportUseCase @Inject constructor(
    private val logRepository: LogRepository,
) {
    suspend operator fun invoke(report: String) {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val timestamp = LocalDateTime.now().format(formatter)

        logRepository.logReport("$timestamp> $report")
    }
}
