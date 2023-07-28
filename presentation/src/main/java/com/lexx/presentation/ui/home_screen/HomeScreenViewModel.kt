package com.lexx.presentation.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexx.domain.features.logger.LogReportUseCase
import com.lexx.presentation.navigation.AppContentType
import com.lexx.presentation.models.home_screen.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val logReportsUseCase: LogReportUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeScreenUiState()
    )

    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            logReportsUseCase("App started")
        }
    }
    fun updateNavigationContent(currentContent: AppContentType) {
        _uiState.value = _uiState.value.copy(currentContent = currentContent)
    }
}
