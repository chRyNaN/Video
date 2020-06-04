package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.VideoResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoAboutViewModel
import javax.inject.Inject

class VideoAboutMapper @Inject constructor() :
    Mapper<VideoResult, VideoInfoAboutViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoAboutViewModel =
        VideoInfoAboutViewModel(
            videoInfo = model.info,
            about = model.video.about
        )
}