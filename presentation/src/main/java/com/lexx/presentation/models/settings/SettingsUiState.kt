package com.lexx.presentation.models.settings

data class SettingsUiState(
    val serverAddress: String = "",
    val username: String = "",
    val password: String = "",
    val shareName: String = "",
    val outputDirectory: String = "",
    val filenameSuffix: String = "",
)
