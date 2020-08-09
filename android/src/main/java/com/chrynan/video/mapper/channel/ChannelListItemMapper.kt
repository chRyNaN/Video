package com.chrynan.video.mapper.channel

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelListItemViewModel
import javax.inject.Inject

class ChannelListItemMapper @Inject constructor() :
    Mapper<String, ChannelListItemViewModel> {

    override suspend fun map(model: String): ChannelListItemViewModel = TODO()
}