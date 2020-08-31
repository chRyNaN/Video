package com.chrynan.video.presentation.navigator

import com.chrynan.video.presentation.core.Screen

sealed class MainScreen : Screen {

    object Home : MainScreen()

    object Search : MainScreen()

    object UserContent : MainScreen()

    object Settings : MainScreen()

    object Video : MainScreen()
}