package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoInfoHeaderViewModel

class VideoHeaderMapper @Inject constructor(
    private val actionsMapper: VideoActionsMapper,
    private val providerMapper: VideoProviderMapper
) : Mapper<String, VideoInfoHeaderViewModel> {

    override suspend fun map(model: String): VideoInfoHeaderViewModel = TODO()
}