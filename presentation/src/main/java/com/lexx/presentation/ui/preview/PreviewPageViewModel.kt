package com.lexx.presentation.ui.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexx.domain.features.logger.GetLogReportsUseCase
import com.lexx.domain.features.preview.GetLastFilenameUseCase
import com.lexx.presentation.models.preview.PreviewPageUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PreviewPageViewModel @Inject constructor(
    val getLogReportsUseCase: GetLogReportsUseCase,
    val getLastFilenameUseCase: GetLastFilenameUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PreviewPageUiState()
    )

    val uiState: StateFlow<PreviewPageUiState> = _uiState.asStateFlow()

    init {
        observeData()
        updateData()
    }

    fun observeData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getLogReportsUseCase().collect {
                    _uiState.value = _uiState.value.copy(logReports = it)
                }
            }
        }
    }

    fun updateData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.value = _uiState.value.copy(lastFilename = getLastFilenameUseCase())
            }
        }
    }
}
