package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoHeaderViewModel
import javax.inject.Inject

class VideoHeaderMapper @Inject constructor(
    private val actionsMapper: VideoActionsMapper,
    private val providerMapper: VideoProviderMapper
) : Mapper<String, VideoInfoHeaderViewModel> {

    override suspend fun map(model: String): VideoInfoHeaderViewModel = TODO()
}