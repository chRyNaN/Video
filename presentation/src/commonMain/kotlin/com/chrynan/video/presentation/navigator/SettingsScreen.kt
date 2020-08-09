package com.chrynan.video.presentation.navigator

sealed class SettingsScreen : Screen {

    object NewService : SettingsScreen()

    object ServiceProviderList : SettingsScreen()
}