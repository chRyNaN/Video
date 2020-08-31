package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoAboutViewModel

class VideoAboutMapper @Inject constructor() :
    Mapper<String, VideoInfoAboutViewModel> {

    override suspend fun map(model: String) = TODO()
}