package com.chrynan.video.model

import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoShowcaseViewModel(
    val videoId: String,
    val channelId: String,
    val providerUrl: String,
    val title: String,
    val details: String,
    val provider: String,
    val videoLength: String,
    val videoImageUrl: String,
    val channelImageUrl: String
) : UniqueAdapterItem {

    override val uniqueAdapterId = "Showcase:$providerUrl:$channelId:$videoId".asUniqueAdapterId()
}