package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class ChannelInfoViewModel(
    val name: String,
    val about: String,
    val created: String,
    val lastUpdated: String,
    val subscriberCount: String,
    val videoCountString: String,
    val totalVideoViewCount: String,
    val showSubscriberCount: Boolean = false,
    val showVideoCount: Boolean = false,
    val showTotalVideoViewCount: Boolean = false,
    val headerImageUri: String,
    val channelImageUri: String,
    val isSubscribed: Boolean = false,
    val providerServiceName: String,
    val channelId: String,
    val providerUri: String,
    val channelUrl: String? = null
) : AdapterItem {

    override val uniqueAdapterId =
        "Channel Info: channelId = $channelId; providerUri = $providerUri".asUniqueAdapterId()
}