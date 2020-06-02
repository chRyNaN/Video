package com.chrynan.video.mapper

import com.chrynan.common.model.VideoResult
import com.chrynan.video.viewmodel.VideoInfoDetailsViewModel
import javax.inject.Inject

class VideoDetailsMapper @Inject constructor() : Mapper<VideoResult, VideoInfoDetailsViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoDetailsViewModel =
        VideoInfoDetailsViewModel(
            videoInfo = model.info,
            category = model.video.category,
            tags = model.video.tags
        )
}