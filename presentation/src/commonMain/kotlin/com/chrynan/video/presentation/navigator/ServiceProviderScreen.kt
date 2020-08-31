package com.chrynan.video.presentation.navigator

import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.Screen

sealed class ServiceProviderScreen : Screen {

    object List : ServiceProviderScreen()

    data class Details(val providerUri: UriString) : ServiceProviderScreen()

    object New : ServiceProviderScreen()
}