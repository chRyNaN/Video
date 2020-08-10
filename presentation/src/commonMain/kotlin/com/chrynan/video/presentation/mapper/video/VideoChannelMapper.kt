package com.chrynan.video.presentation.mapper.video

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoChannelViewModel

class VideoChannelMapper @Inject constructor() :
    Mapper<String, VideoInfoChannelViewModel> {

    override suspend fun map(model: String): VideoInfoChannelViewModel = TODO()
}