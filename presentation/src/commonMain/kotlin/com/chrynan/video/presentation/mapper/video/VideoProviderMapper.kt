package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoProviderViewModel

class VideoProviderMapper @Inject constructor() :
    Mapper<String, VideoInfoProviderViewModel> {

    override suspend fun map(model: String): VideoInfoProviderViewModel = TODO()
}