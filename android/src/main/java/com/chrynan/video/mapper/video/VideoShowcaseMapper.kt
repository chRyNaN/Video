package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import javax.inject.Inject

class VideoShowcaseMapper @Inject constructor() :
    Mapper<String, VideoShowcaseViewModel> {

    override suspend fun map(model: String): VideoShowcaseViewModel = TODO()
}