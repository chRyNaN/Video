package com.chrynan.video.presentation.mapper.channel

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelVideoListViewModel

class ChannelVideoListItemMapper @Inject constructor() :
    Mapper<String, ChannelVideoListViewModel.ChannelVideoListItemViewModel> {

    override suspend fun map(model: String): ChannelVideoListViewModel.ChannelVideoListItemViewModel =
        TODO()
}