package com.lexx.presentation.ui.camera

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexx.domain.features.camera.GetCameraPermissionGrantedUseCase
import com.lexx.domain.features.logger.LogReportUseCase
import com.lexx.domain.features.preview.SetLastFilenameUseCase
import com.lexx.domain.features.samba.CopyFileUseCase
import com.lexx.domain.features.settings.GetFilenameSuffixUseCase
import com.lexx.presentation.models.camera.CameraPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CameraPageViewModel @Inject constructor(
    private val logReportUseCase: LogReportUseCase,
    private val getCameraPermissionGrantedUseCase: GetCameraPermissionGrantedUseCase,
    private val copyFileUseCase: CopyFileUseCase,
    private val getFilenameSuffixUseCase: GetFilenameSuffixUseCase,
    private val setLastFilenameUseCase: SetLastFilenameUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(
        CameraPageUiState()
    )

    val uiState: StateFlow<CameraPageUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.value = _uiState.value.copy(
                    prefix = getFilenameSuffixUseCase(),
                )

                getCameraPermissionGrantedUseCase().collect { granted ->
                    if (granted) {
                        _uiState.value = _uiState.value.copy(permissionGranted = true)
                    } else {
                        _uiState.value = _uiState.value.copy(permissionGranted = false)
                    }
                }
            }
        }
    }

    fun onImageCaptured(
        uri: Uri,
        onPreview: () -> Unit,
        updateData: () -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logReportUseCase("Image captured:\n$uri")
                copyFileUseCase(uri.path!!)
                logReportUseCase("file copied: ${uri.lastPathSegment!!}")
                setLastFilenameUseCase(uri.toString())
                updateData()
                onPreview()
            }
        }
    }

    fun onError(
        error: String,
        onPreview: () -> Unit,
        updateData: () -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logReportUseCase("$error")
                updateData()
                onPreview()
            }
        }
    }
}
