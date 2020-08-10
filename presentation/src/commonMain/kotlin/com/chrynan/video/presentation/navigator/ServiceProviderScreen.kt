package com.chrynan.video.presentation.navigator

import com.chrynan.video.common.model.core.UriString

sealed class ServiceProviderScreen : Screen {

    object List : ServiceProviderScreen()

    data class Details(val providerUri: UriString) : ServiceProviderScreen()

    object New : ServiceProviderScreen()
}