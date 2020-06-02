package com.chrynan.video.mapper

import com.chrynan.common.model.VideoResult
import com.chrynan.video.viewmodel.VideoInfoProviderViewModel
import javax.inject.Inject

class VideoProviderMapper @Inject constructor() : Mapper<VideoResult, VideoInfoProviderViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoProviderViewModel =
        VideoInfoProviderViewModel(
            videoInfo = model.info,
            providerServiceName = model.provider.name
        )
}