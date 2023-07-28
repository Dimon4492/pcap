package com.lexx.domain.features.logger

import kotlinx.coroutines.flow.Flow

interface LogRepository {
    fun getLogReports() : Flow<List<String>>
    suspend fun logReport(report: String)
}