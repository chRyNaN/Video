package com.chrynan.video.model

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem

data class VideoRecommendationViewModel(
    override val uniqueAdapterId: AdapterId = 1L,
    val title: String,
    val channelName: String,
    val detailText: String,
    val videoId: String,
    val channelId: String,
    val providerUrl: String,
    val videoImageUrl: String,
    val videoLength: String
) : UniqueAdapterItem