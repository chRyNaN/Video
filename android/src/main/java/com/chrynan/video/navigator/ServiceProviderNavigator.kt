package com.chrynan.video.navigator

import com.chrynan.common.model.core.UriString

interface ServiceProviderNavigator : Navigator {

    fun goToServiceList()

    fun goToAddNewService()

    fun goToServiceDetails(providerUri: UriString)
}