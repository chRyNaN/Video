package com.chrynan.video.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.common.model.api.VideoInfo

data class VideoInfoProviderViewModel(
    val videoInfo: VideoInfo,
    val providerServiceName: String
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = videoInfo.asUniqueAdapterId()
}