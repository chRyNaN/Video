package com.chrynan.video.presentation.mapper.channel

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelListItemViewModel

class ChannelListItemMapper @Inject constructor() :
    Mapper<String, ChannelListItemViewModel> {

    override suspend fun map(model: String): ChannelListItemViewModel = TODO()
}