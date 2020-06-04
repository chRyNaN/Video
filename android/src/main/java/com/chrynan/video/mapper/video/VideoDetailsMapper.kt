package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.VideoResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoDetailsViewModel
import javax.inject.Inject

class VideoDetailsMapper @Inject constructor() :
    Mapper<VideoResult, VideoInfoDetailsViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoDetailsViewModel =
        VideoInfoDetailsViewModel(
            videoInfo = model.info,
            category = model.video.category,
            tags = model.video.tags
        )
}