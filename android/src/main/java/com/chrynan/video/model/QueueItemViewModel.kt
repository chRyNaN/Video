package com.chrynan.video.model

import android.net.Uri
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class QueueItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: Uri,
    val title: String,
    val isActivated: Boolean,
    val position: Int
) : UniqueAdapterItem {

    override val uniqueAdapterId =
        "Queue Item: videoInfo = $videoInfo; position = $position; isActivated = $isActivated".asUniqueAdapterId()
}