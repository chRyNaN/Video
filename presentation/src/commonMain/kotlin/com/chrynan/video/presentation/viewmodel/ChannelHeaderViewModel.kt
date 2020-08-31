package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.presentation.core.AdapterItem

data class ChannelHeaderViewModel(
    val channelId: String,
    val providerUri: UriString,
    val name: String,
    val subscriberCount: String,
    val totalVideoViewCount: String,
    val channelHeaderImage: UriString? = null,
    val channelImage: UriString? = null,
    val isSubscribed: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId =
        "ChannelHeaderViewModel:$channelId;$providerUri".asUniqueAdapterId()
}