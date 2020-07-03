package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoChannelViewModel
import javax.inject.Inject

class VideoChannelMapper @Inject constructor() :
    Mapper<String, VideoInfoChannelViewModel> {

    override suspend fun map(model: String): VideoInfoChannelViewModel = TODO()
}