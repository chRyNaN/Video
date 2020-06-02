package com.chrynan.video.mapper

import com.chrynan.common.model.VideoResult
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import javax.inject.Inject

class VideoRecommendationMapper @Inject constructor() :
    Mapper<VideoResult, VideoRecommendationViewModel> {

    override suspend fun map(model: VideoResult): VideoRecommendationViewModel =
        VideoRecommendationViewModel(
            videoInfo = model.info,
            title = model.video.name,
            channelName = model.channel.name,
            detailText = "", // TODO update
            videoLength = getVideoLengthText(model)
        )

    private fun getVideoLengthText(model: VideoResult): String = "" // TODO update
}