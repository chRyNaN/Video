package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class ChannelInfoViewModel(
    val about: String? = null,
    val created: String,
    val lastUpdated: String,
    val headerImageUri: String? = null,
    val channelImageUri: String? = null,
    val isSubscribed: Boolean = false,
    val channelId: String,
    val providerUri: String,
    val channelUrl: String? = null
) : AdapterItem {

    override val uniqueAdapterId =
        "Channel Info: channelId = $channelId; providerUri = $providerUri".asUniqueAdapterId()
}