package com.chrynan.common.model.api

import kotlinx.serialization.Serializable

@Serializable
sealed class FeedItem {

    @Serializable
    class VideoFeedItem(
        val videoInfo: VideoInfo,
        val videoName: String,
        val videoDescription: String? = null,
        val isVideoLive: Boolean = false,
        val videoLengthInMilliseconds: Long? = null,
        val videoViewCount: Long? = null,
        val channelName: String,
        val isSubscribedToChannel: Boolean = false,
        val channelSubscriberCount: Long,
        val providerName: String
    ) : FeedItem()
}