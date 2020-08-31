package com.chrynan.video.presentation.mapper.channel

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelListItemViewModel

class ChannelListItemMapper @Inject constructor() :
    Mapper<String, ChannelListItemViewModel> {

    override suspend fun map(model: String): ChannelListItemViewModel = TODO()
}