package com.lexx.presentation.models.camera

import androidx.camera.core.CameraSelector

data class CameraPageUiState(
    val prefix: String = "",
    val lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    val permissionGranted: Boolean = false
)
