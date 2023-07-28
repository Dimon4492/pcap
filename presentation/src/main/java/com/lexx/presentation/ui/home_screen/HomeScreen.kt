package com.lexx.presentation.ui.home_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexx.presentation.R
import com.lexx.presentation.models.navigation.NavigationItemContent
import com.lexx.presentation.navigation.AppContentType
import com.lexx.presentation.navigation.AppNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appNavigationType: AppNavigationType = AppNavigationType.NAVIGATION_RAIL,
    homeScreenViewModel: HomeScreenViewModel = viewModel()
) {
    val uiState = homeScreenViewModel.uiState.collectAsState().value

    val navigationItemContentList = listOf(
        NavigationItemContent(
            AppContentType.CAMERA_CONTENT_TYPE,
            R.drawable.baseline_camera_24,
            stringResource(id = R.string.camera_label)
        ),
        NavigationItemContent(
            AppContentType.PREVIEW_CONTENT_TYPE,
            R.drawable.baseline_preview_24,
            stringResource(id = R.string.preview_label)
        ),
        NavigationItemContent(
            AppContentType.SETTINGS_CONTENT_TYPE,
            R.drawable.baseline_settings_24,
            stringResource(id = R.string.settings_label)
        ),
    )

    val title = navigationItemContentList.find { navigationItemContent ->
        navigationItemContent.appContentType == uiState.currentContent
    }?.tabLabel ?: ""

    Scaffold(
        content = {
            NavigationLayout(
                modifier.padding(it),
                appNavigationType,
                uiState.currentContent,
                navigationItemContentList,
                onTabPressed = {
                    homeScreenViewModel.updateNavigationContent(it)
                },
            )
        },
        topBar = { TopAppBar(title = { Text(title) }) }
    )
}
