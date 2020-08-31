package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.core.AdapterItem

data class VideoInfoChannelViewModel(
    val videoInfo: VideoInfo,
    val channelName: String,
    val channelImageUrl: String? = null,
    val channelSubscriberCount: String,
    val isSubscribedToChannel: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = "ChannelInfo:$videoInfo".asUniqueAdapterId()
}