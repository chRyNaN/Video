package com.chrynan.video.presentation.navigator

import com.chrynan.video.presentation.core.Screen

sealed class SettingsScreen : Screen {

    object NewService : SettingsScreen()

    object ServiceProviderList : SettingsScreen()
}