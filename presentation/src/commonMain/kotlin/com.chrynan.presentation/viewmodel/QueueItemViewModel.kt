package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class QueueItemViewModel(
    val videoInfo: VideoInfo,
    val videoImageUri: String,
    val title: String,
    val isActivated: Boolean,
    val position: Int
) : AdapterItem {

    override val uniqueAdapterId =
        "Queue Item: videoInfo = $videoInfo; position = $position; isActivated = $isActivated".asUniqueAdapterId()
}