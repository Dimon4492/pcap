package com.lexx.presentation.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexx.presentation.navigation.AppNavigationType
import com.lexx.presentation.ui.home_screen.HomeScreen

@Composable
fun PictureCapApp(
    modifier: Modifier,
    widthSizeClass: WindowWidthSizeClass,
) {
    val appNavigationType = when(widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AppNavigationType.BOTTOM_NAVIGATION
        }
        else -> {
            AppNavigationType.NAVIGATION_RAIL
        }
    }

    HomeScreen(
        appNavigationType = appNavigationType,
        modifier = modifier
    )
}
