package com.chrynan.video.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoChannelViewModel(
    val videoInfo: VideoInfo,
    val channelName: String,
    val channelImageUrl: String,
    val channelSubscriberCount: String,
    val showChannelSubscribeCount: Boolean = false,
    val isSubscribedToChannel: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "ChannelInfo:$videoInfo".asUniqueAdapterId()
}