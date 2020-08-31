package com.chrynan.video.presentation.navigator

import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.Screen

sealed class HomeScreen : Screen {

    data class Video(
        val providerUri: UriString,
        val videoId: ID
    ) : HomeScreen()
}