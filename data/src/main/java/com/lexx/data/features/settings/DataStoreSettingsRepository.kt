package com.lexx.data.features.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.lexx.domain.features.settings.SettingsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreSettingsRepository @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : SettingsRepository {
    private val serverAddressPreferencesKey = stringPreferencesKey(name = "server_address")
    private val loginPreferencesKey = stringPreferencesKey(name = "login")
    private val passwordPreferencesKey = stringPreferencesKey(name = "password")
    private val shareNamePreferencesKey = stringPreferencesKey(name = "share_name")
    private val outputDirectoryPreferencesKey = stringPreferencesKey(name = "output_directory")
    private val filenameSuffixPreferencesKey = stringPreferencesKey(name = "filename_suffix")
    private val lastFilenamePreferencesKey = stringPreferencesKey(name = "last_filename")

    override suspend fun getServerAddress(): String {
        return dataStorePreferences.data.first()[serverAddressPreferencesKey] ?: ""
    }

    override suspend fun setServerAddress(serverAddress: String) {
        dataStorePreferences.edit { it ->
            it[serverAddressPreferencesKey] = serverAddress
        }
    }

    override suspend fun getLogin(): String {
        return dataStorePreferences.data.first()[loginPreferencesKey] ?: ""
    }

    override suspend fun setLogin(login: String) {
        dataStorePreferences.edit { it ->
            it[loginPreferencesKey] = login
        }
    }

    override suspend fun getPassword(): String {
        return dataStorePreferences.data.first()[passwordPreferencesKey] ?: ""
    }

    override suspend fun setPassword(password: String) {
        dataStorePreferences.edit { it ->
            it[passwordPreferencesKey] = password
        }
    }

    override suspend fun getShareName(): String {
        return dataStorePreferences.data.first()[shareNamePreferencesKey] ?: ""
    }

    override suspend fun setShareName(shareName: String) {
        dataStorePreferences.edit { it ->
            it[shareNamePreferencesKey] = shareName
        }
    }

    override suspend fun getLastFilename(): String {
        return dataStorePreferences.data.first()[lastFilenamePreferencesKey] ?: ""

    }

    override suspend fun setLastFilename(filename: String) {
        dataStorePreferences.edit { it ->
            it[lastFilenamePreferencesKey] = filename
        }
    }

    override suspend fun getOutputDirectory(): String {
        return dataStorePreferences.data.first()[outputDirectoryPreferencesKey] ?: ""
    }

    override suspend fun setOutputDirectory(outputDirectory: String) {
        dataStorePreferences.edit { it ->
            it[outputDirectoryPreferencesKey] = outputDirectory
        }
    }

    override suspend fun getFilenameSuffix(): String {
        return dataStorePreferences.data.first()[filenameSuffixPreferencesKey] ?: ""
    }

    override suspend fun setFilenameSuffix(filenameSuffix: String) {
        dataStorePreferences.edit { it ->
            it[filenameSuffixPreferencesKey] = filenameSuffix
        }
    }
}
