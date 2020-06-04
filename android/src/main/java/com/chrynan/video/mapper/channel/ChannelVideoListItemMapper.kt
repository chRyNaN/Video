package com.chrynan.video.mapper.channel

import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelVideoListViewModel
import javax.inject.Inject

class ChannelVideoListItemMapper @Inject constructor() :
    Mapper<VideoResult, ChannelVideoListViewModel.ChannelVideoListItemViewModel> {

    override suspend fun map(model: VideoResult): ChannelVideoListViewModel.ChannelVideoListItemViewModel =
        ChannelVideoListViewModel.ChannelVideoListItemViewModel(
            videoInfo = model.info,
            title = model.video.name,
            description = model.video.description // TODO update
        )
}