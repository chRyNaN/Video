package com.chrynan.video.presentation.mapper.channel

import com.chrynan.video.common.Inject
import com.chrynan.video.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelProviderViewModel

class ChannelProviderMapper @Inject constructor() :
    Mapper<String, ChannelProviderViewModel> {

    override suspend fun map(model: String): ChannelProviderViewModel = TODO()
}