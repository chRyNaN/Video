package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.AdapterItem

data class ChannelProviderViewModel(
    val channelId: String,
    val providerUri: UriString,
    val providerServiceName: String
) : AdapterItem {

    override val uniqueAdapterId = "ChannelProvider:$channelId;$providerUri".asUniqueAdapterId()
}