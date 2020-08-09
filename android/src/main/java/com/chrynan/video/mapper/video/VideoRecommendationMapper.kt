package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoRecommendationViewModel
import javax.inject.Inject

class VideoRecommendationMapper @Inject constructor() :
    Mapper<String, VideoRecommendationViewModel> {

    override suspend fun map(model: String): VideoRecommendationViewModel = TODO()
}