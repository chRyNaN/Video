package com.chrynan.video.model

import android.net.Uri
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class WatchListItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: Uri,
    val title: String,
    val description: String,
    val secondaryDescription: String
) : UniqueAdapterItem {

    override val uniqueAdapterId = "Watch List Item: videoInfo = $videoInfo".asUniqueAdapterId()
}