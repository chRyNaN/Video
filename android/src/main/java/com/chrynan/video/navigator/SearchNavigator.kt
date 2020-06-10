package com.chrynan.video.navigator

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString

interface SearchNavigator : Navigator {

    fun goToChannel(providerUri: UriString, channelId: ID)
}