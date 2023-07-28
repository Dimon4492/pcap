package com.lexx.presentation.models.home_screen

import com.lexx.presentation.navigation.AppContentType

data class HomeScreenUiState (
    val currentContent: AppContentType = AppContentType.CAMERA_CONTENT_TYPE,
)
