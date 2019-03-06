package com.chrynan.video.model

import android.net.Uri
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.utils.ChannelId
import com.chrynan.video.utils.ProviderUri

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
    val headerImageUri: Uri,
    val channelImageUri: Uri,
    val isSubscribed: Boolean = false,
    val providerServiceName: String,
    val channelId: ChannelId,
    val providerUri: ProviderUri,
    val channelUrl: String? = null
) : UniqueAdapterItem {

    override val uniqueAdapterId =
        "Channel Info: channelId = $channelId; providerUri = $providerUri".asUniqueAdapterId()
}