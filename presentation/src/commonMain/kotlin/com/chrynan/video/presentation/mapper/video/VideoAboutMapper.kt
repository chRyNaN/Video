package com.chrynan.video.presentation.mapper.video

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoAboutViewModel

class VideoAboutMapper @Inject constructor() :
    Mapper<String, VideoInfoAboutViewModel> {

    override suspend fun map(model: String) = TODO()
}