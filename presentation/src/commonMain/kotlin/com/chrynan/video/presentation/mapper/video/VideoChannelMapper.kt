package com.chrynan.video.presentation.mapper.video

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoChannelViewModel

class VideoChannelMapper @Inject constructor() :
    Mapper<String, VideoInfoChannelViewModel> {

    override suspend fun map(model: String): VideoInfoChannelViewModel = TODO()
}