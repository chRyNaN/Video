package com.chrynan.video.mapper.channel

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelVideoListViewModel
import javax.inject.Inject

class ChannelVideoListItemMapper @Inject constructor() :
    Mapper<String, ChannelVideoListViewModel.ChannelVideoListItemViewModel> {

    override suspend fun map(model: String): ChannelVideoListViewModel.ChannelVideoListItemViewModel = TODO()
}