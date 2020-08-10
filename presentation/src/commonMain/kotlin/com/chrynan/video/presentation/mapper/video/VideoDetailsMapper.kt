package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoDetailsViewModel

class VideoDetailsMapper @Inject constructor() :
    Mapper<String, VideoInfoDetailsViewModel> {

    override suspend fun map(model: String): VideoInfoDetailsViewModel = TODO()
}