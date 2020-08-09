package com.chrynan.video.presentation.navigator

sealed class ServiceProviderListScreen : Screen {

    object NewService : ServiceProviderListScreen()

    object ServiceDetails : ServiceProviderListScreen()
}