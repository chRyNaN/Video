package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoProviderViewModel
import javax.inject.Inject

class VideoProviderMapper @Inject constructor() :
    Mapper<VideoResult, VideoInfoProviderViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoProviderViewModel =
        VideoInfoProviderViewModel(
            videoInfo = model.info,
            providerServiceName = model.provider.name
        )
}