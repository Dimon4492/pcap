package com.lexx.domain.features.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getServerAddress(): String
    suspend fun setServerAddress(serverAddress: String)

    suspend fun getFilenameSuffix(): String
    suspend fun setFilenameSuffix(filenameSuffix: String)

    suspend fun getOutputDirectory(): String
    suspend fun setOutputDirectory(outputDirectory: String)

    suspend fun getLogin(): String
    suspend fun setLogin(login: String)

    suspend fun getPassword(): String
    suspend fun setPassword(password: String)

    suspend fun getShareName(): String
    suspend fun setShareName(shareName: String)

    suspend fun getLastFilename(): String
    suspend fun setLastFilename(filename: String)
}
