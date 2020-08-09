package com.chrynan.video.presentation.navigator

sealed class MainScreen : Screen {

    object Home : MainScreen()

    object Search : MainScreen()

    object UserContent : MainScreen()

    object Settings : MainScreen()

    object Video : MainScreen()
}