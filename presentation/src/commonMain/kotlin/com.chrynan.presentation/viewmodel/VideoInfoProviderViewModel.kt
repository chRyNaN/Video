package com.chrynan.presentation.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.asUniqueAdapterId

data class VideoInfoProviderViewModel(
    val videoInfo: VideoInfo,
    val providerServiceName: String
) : AdapterItem {

    override val uniqueAdapterId: AdapterId = videoInfo.asUniqueAdapterId()
}