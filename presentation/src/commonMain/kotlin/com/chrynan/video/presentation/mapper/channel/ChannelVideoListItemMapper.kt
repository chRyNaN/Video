package com.chrynan.video.presentation.mapper.channel

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelVideoListViewModel

class ChannelVideoListItemMapper @Inject constructor() :
    Mapper<String, ChannelVideoListViewModel.ChannelVideoListItemViewModel> {

    override suspend fun map(model: String): ChannelVideoListViewModel.ChannelVideoListItemViewModel =
        TODO()
}