package com.lexx.presentation.ui.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lexx.presentation.models.navigation.NavigationItemContent
import com.lexx.presentation.navigation.AppContentType
import com.lexx.presentation.navigation.AppNavigationType

@Composable
fun NavigationLayout(
    modifier: Modifier,
    appNavigationType: AppNavigationType,
    currentContent: AppContentType,
    navigationItemContentList: List<NavigationItemContent>,
    onTabPressed: (AppContentType) -> Unit,
) {
    Row(modifier) {
        AnimatedVisibility(visible = appNavigationType == AppNavigationType.NAVIGATION_RAIL) {
            LeftNavigationRail(
                currentTab = currentContent,
                onTabPressed = onTabPressed,
                navigationItemContentList = navigationItemContentList
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            AppContent(
                contentType = currentContent,
                modifier = Modifier.weight(1.0F),
                onPreview = { onTabPressed(AppContentType.PREVIEW_CONTENT_TYPE) },
            )

            AnimatedVisibility(
                visible = appNavigationType == AppNavigationType.BOTTOM_NAVIGATION
            ) {
                BottomNavigationBar(
                    currentTab = currentContent,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList,
                )
            }
        }
    }
}

