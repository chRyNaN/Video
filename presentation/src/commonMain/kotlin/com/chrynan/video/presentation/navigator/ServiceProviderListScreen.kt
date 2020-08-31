package com.chrynan.video.presentation.navigator

import com.chrynan.video.presentation.core.Screen

sealed class ServiceProviderListScreen : Screen {

    object NewService : ServiceProviderListScreen()

    object ServiceDetails : ServiceProviderListScreen()
}