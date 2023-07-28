package com.lexx.presentation.ui.camera.util

import android.content.Context
import com.lexx.presentation.R
import java.io.File

fun getOutputDirectory(
    context: Context
): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}
