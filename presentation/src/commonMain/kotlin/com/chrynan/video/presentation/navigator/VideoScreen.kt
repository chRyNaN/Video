package com.chrynan.video.presentation.navigator

import com.chrynan.video.presentation.core.Screen

sealed class VideoScreen : Screen {

    object Video : VideoScreen()
}