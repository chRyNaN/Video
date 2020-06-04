package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import javax.inject.Inject

class VideoShowcaseMapper @Inject constructor() :
    Mapper<VideoResult, VideoShowcaseViewModel> {

    override suspend fun map(model: VideoResult): VideoShowcaseViewModel =
        VideoShowcaseViewModel(
            videoInfo = model.info,
            title = model.video.name,
            details = "", // TODO update
            provider = model.provider.name,
            videoLength = getVideoLengthText(model),
            channelImageUrl = model.channel.images.thumbnail
        )

    private fun getVideoLengthText(model: VideoResult): String = "" // TODO update
}