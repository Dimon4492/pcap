package com.lexx.data.features.settings

import com.lexx.domain.features.logger.LogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MemoryLogRepository @Inject constructor() : LogRepository {
    private val reports: MutableList<String> = mutableListOf()
    private val _reportsStateFlow = MutableStateFlow<List<String>>(listOf())
    val reportsStateFlow: StateFlow<List<String>> = _reportsStateFlow

    override fun getLogReports(): Flow<List<String>> {
        return reportsStateFlow
    }

    override suspend fun logReport(report: String) {
        reports.add(0, report)
        _reportsStateFlow.emit(reports)
    }
}
