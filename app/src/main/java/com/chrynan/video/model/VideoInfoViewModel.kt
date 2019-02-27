package com.chrynan.video.model

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem

data class VideoInfoViewModel(
    val videoId: String,
    val channelId: String,
    val providerUrl: String,
    override val uniqueAdapterId: AdapterId = -1L,
    val title: String,
    val viewCount: String,
    val description: String,
    val publishedDate: String,
    val category: String? = null,
    val tags: List<String> = emptyList(),
    val supportsRating: Boolean = false,
    val likeButtonText: String,
    val dislikeButtonText: String,
    val shareButtonText: String,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
    val providerServiceName: String,
    val channelName: String,
    val channelImageUrl: String,
    val channelSubscriberCount: String? = null,
    val isSubscribedToChannel: Boolean = false
) : UniqueAdapterItem