package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class ChannelInfoViewModel(
    val about: String,
    val created: String,
    val lastUpdated: String,
    val headerImageUri: String,
    val channelImageUri: String,
    val isSubscribed: Boolean = false,
    val channelId: String,
    val providerUri: String,
    val channelUrl: String? = null
) : AdapterItem {

    override val uniqueAdapterId =
        "Channel Info: channelId = $channelId; providerUri = $providerUri".asUniqueAdapterId()
}