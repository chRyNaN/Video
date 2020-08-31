package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.common.model.api.VideoAction
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.presentation.model.ResourceID

data class VideoInfoActionViewModel(
    val videoInfo: VideoInfo,
    val action: VideoAction,
    val icon: ResourceID
) : AdapterItem {

    override val uniqueAdapterId = "VideoAction:$videoInfo$action".asUniqueAdapterId()
}