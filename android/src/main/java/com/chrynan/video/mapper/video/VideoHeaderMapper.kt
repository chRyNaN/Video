package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.Video
import com.chrynan.common.model.api.VideoResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoHeaderViewModel
import javax.inject.Inject

class VideoHeaderMapper @Inject constructor(
    private val actionsMapper: VideoActionsMapper,
    private val providerMapper: VideoProviderMapper
) : Mapper<VideoResult, VideoInfoHeaderViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoHeaderViewModel =
        VideoInfoHeaderViewModel(
            videoInfo = model.info,
            title = model.video.name,
            detail = getDetailText(model.video),
            actions = actionsMapper.map(model),
            provider = providerMapper.map(model)
        )

    private fun getDetailText(video: Video): String = ""
}