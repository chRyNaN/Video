package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoProviderViewModel
import javax.inject.Inject

class VideoProviderMapper @Inject constructor() :
    Mapper<String, VideoInfoProviderViewModel> {

    override suspend fun map(model: String): VideoInfoProviderViewModel = TODO()
}