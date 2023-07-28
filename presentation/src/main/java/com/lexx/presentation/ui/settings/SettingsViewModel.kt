package com.lexx.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexx.domain.features.settings.GetFilenameSuffixUseCase
import com.lexx.domain.features.settings.GetOutputDirectoryUseCase
import com.lexx.domain.features.settings.GetServerAddressUseCase
import com.lexx.domain.features.settings.GetUsernameUseCase
import com.lexx.domain.features.settings.GetPasswordUseCase
import com.lexx.domain.features.settings.GetShareNameUseCase
import com.lexx.domain.features.settings.SetFilenameSuffixUseCase
import com.lexx.domain.features.settings.SetOutputDirectoryUseCase
import com.lexx.domain.features.settings.SetServerAddressUseCase
import com.lexx.domain.features.settings.SetUsernameUseCase
import com.lexx.domain.features.settings.SetPasswordUseCase
import com.lexx.domain.features.settings.SetShareNameUseCase
import com.lexx.presentation.models.settings.SettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getServerAddressUseCase: GetServerAddressUseCase,
    private val setServerAddressUseCase: SetServerAddressUseCase,
    private val getShareNameUseCase: GetShareNameUseCase,
    private val setShareNameUseCase: SetShareNameUseCase,
    private val getOutputDirectoryUseCase: GetOutputDirectoryUseCase,
    private val setOutputDirectoryUseCase: SetOutputDirectoryUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    private val setUsernameUseCase: SetUsernameUseCase,
    private val getPasswordUseCase: GetPasswordUseCase,
    private val setPasswordUseCase: SetPasswordUseCase,
    private val getFilenameSuffixUseCase: GetFilenameSuffixUseCase,
    private val setFilenameSuffixUseCase: SetFilenameSuffixUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.value = _uiState.value.copy(
                    serverAddress = getServerAddressUseCase(),
                    username = getUsernameUseCase(),
                    password = getPasswordUseCase(),
                    shareName = getShareNameUseCase(),
                    outputDirectory = getOutputDirectoryUseCase(),
                    filenameSuffix = getFilenameSuffixUseCase(),
                )
            }
        }
    }

    fun setServerAddress(serverAddress: String) {
        _uiState.value = _uiState.value.copy(serverAddress = serverAddress)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setServerAddressUseCase(serverAddress)
            }
        }
    }

    fun setUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setUsernameUseCase(username)
            }
        }
    }

    fun setPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setPasswordUseCase(password)
            }
        }
    }

    fun setShareName(shareName: String) {
        _uiState.value = _uiState.value.copy(shareName = shareName)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setShareNameUseCase(shareName)

            }
        }
    }

    fun setOutputDirectory(outputDirectory: String) {
        _uiState.value = _uiState.value.copy(outputDirectory = outputDirectory)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setOutputDirectoryUseCase(outputDirectory)
            }
        }
    }

    fun setFilenameSuffix(filenameSuffix: String) {
        _uiState.value = _uiState.value.copy(filenameSuffix = filenameSuffix)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setFilenameSuffixUseCase(filenameSuffix)
            }
        }
    }
}
