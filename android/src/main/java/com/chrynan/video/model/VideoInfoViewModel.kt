package com.chrynan.video.model

import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoViewModel(
    val videoInfo: VideoInfo,
    val title: String,
    val viewCount: String,
    val description: String,
    val publishedDate: String,
    val category: String,
    val showCategory: Boolean = false,
    val tags: List<String> = emptyList(),
    val showTags: Boolean = false,
    val supportsRating: Boolean = false,
    val likeButtonText: String,
    val dislikeButtonText: String,
    val shareButtonText: String,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val providerServiceName: String,
    val channelName: String,
    val channelImageUrl: String,
    val channelSubscriberCount: String,
    val showChannelSubscribeCount: Boolean = false,
    val isSubscribedToChannel: Boolean = false
) : UniqueAdapterItem {

    override val uniqueAdapterId = videoInfo.run { "Info:$providerUri:$channelId:$videoId".asUniqueAdapterId() }
}