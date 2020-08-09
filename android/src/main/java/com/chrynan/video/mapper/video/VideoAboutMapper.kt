package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoAboutViewModel
import javax.inject.Inject

class VideoAboutMapper @Inject constructor() :
    Mapper<String, VideoInfoAboutViewModel> {

    override suspend fun map(model: String) = TODO()
}