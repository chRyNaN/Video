package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class WatchListItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: String,
    val title: String,
    val description: String,
    val secondaryDescription: String
) : AdapterItem {

    override val uniqueAdapterId = "Watch List Item: videoInfo = $videoInfo".asUniqueAdapterId()
}