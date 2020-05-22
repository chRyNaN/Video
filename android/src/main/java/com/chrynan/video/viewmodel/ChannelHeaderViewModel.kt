package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.core.UriString

data class ChannelHeaderViewModel(
    val channelId: String,
    val providerUri: UriString,
    val name: String,
    val subscriberCount: String,
    val totalVideoViewCount: String,
    val channelHeaderImage: UriString? = null,
    val channelImage: UriString? = null
) : AdapterItem {

    override val uniqueAdapterId =
        "ChannelHeaderViewModel:$channelId;$providerUri".asUniqueAdapterId()
}