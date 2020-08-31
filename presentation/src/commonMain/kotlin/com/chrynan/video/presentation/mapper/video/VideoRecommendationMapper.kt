package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoRecommendationViewModel

class VideoRecommendationMapper @Inject constructor() :
    Mapper<String, VideoRecommendationViewModel> {

    override suspend fun map(model: String): VideoRecommendationViewModel = TODO()
}