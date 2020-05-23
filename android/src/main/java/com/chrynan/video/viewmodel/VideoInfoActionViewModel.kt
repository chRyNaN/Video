package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.VideoAction
import com.chrynan.common.model.VideoInfo

data class VideoInfoActionViewModel(
    val videoInfo: VideoInfo,
    val action: VideoAction
) : AdapterItem {

    override val uniqueAdapterId = "VideoAction:$videoInfo$action".asUniqueAdapterId()
}