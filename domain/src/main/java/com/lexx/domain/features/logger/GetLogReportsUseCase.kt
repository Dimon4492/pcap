package com.lexx.domain.features.logger

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLogReportsUseCase @Inject constructor(
    private val logRepository: LogRepository,
) {
    operator fun invoke() = logRepository.getLogReports()
}