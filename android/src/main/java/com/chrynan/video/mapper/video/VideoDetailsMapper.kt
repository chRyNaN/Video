package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoDetailsViewModel
import javax.inject.Inject

class VideoDetailsMapper @Inject constructor() :
    Mapper<String, VideoInfoDetailsViewModel> {

    override suspend fun map(model: String): VideoInfoDetailsViewModel = TODO()
}