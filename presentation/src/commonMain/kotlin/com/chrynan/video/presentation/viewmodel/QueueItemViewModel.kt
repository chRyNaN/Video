package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.core.AdapterItem

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