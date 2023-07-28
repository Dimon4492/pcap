package com.lexx.presentation.ui.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexx.presentation.navigation.AppContentType
import com.lexx.presentation.ui.camera.CameraPage
import com.lexx.presentation.ui.preview.PreviewPage
import com.lexx.presentation.ui.settings.SettingsPage

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    contentType: AppContentType = AppContentType.SETTINGS_CONTENT_TYPE,
    onPreview: () -> Unit
) {
    when(contentType) {
        AppContentType.SETTINGS_CONTENT_TYPE -> {
            SettingsPage(modifier)
        }

        AppContentType.PREVIEW_CONTENT_TYPE -> {
            PreviewPage(modifier)
        }

        AppContentType.CAMERA_CONTENT_TYPE -> {
            CameraPage(modifier, onPreview)
        }
    }
}
