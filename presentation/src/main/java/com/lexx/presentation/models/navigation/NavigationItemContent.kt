package com.lexx.presentation.models.navigation

import com.lexx.presentation.navigation.AppContentType

data class NavigationItemContent (
    val appContentType: AppContentType,
    val tabIcon: Int,
    val tabLabel: String,
)
