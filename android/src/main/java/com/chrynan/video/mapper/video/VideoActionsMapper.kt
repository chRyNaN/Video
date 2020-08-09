package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoActionViewModel
import javax.inject.Inject

class VideoActionsMapper @Inject constructor() :
    Mapper<String, List<VideoInfoActionViewModel>> {

    override suspend fun map(model: String): List<VideoInfoActionViewModel> = TODO()
}