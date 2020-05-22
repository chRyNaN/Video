package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.core.UriString

data class ChannelProviderViewModel(
    val channelId: String,
    val providerUri: UriString,
    val providerServiceName: String
) : AdapterItem {

    override val uniqueAdapterId = "ChannelProvider:$channelId;$providerUri".asUniqueAdapterId()
}