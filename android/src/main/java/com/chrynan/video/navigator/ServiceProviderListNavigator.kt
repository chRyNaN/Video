package com.chrynan.video.navigator

import com.chrynan.common.model.core.UriString

interface ServiceProviderListNavigator : Navigator {

    fun goToAddNewService()

    fun goToServiceDetails(providerUri: UriString)
}