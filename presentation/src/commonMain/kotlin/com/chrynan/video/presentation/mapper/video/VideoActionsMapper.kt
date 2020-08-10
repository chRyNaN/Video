package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoActionViewModel

class VideoActionsMapper @Inject constructor() :
    Mapper<String, List<VideoInfoActionViewModel>> {

    override suspend fun map(model: String): List<VideoInfoActionViewModel> = TODO()
}