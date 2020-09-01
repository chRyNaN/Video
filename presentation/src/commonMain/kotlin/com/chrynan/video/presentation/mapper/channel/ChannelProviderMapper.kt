package com.chrynan.video.presentation.mapper.channel

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelProviderViewModel

class ChannelProviderMapper @Inject constructor() :
    Mapper<String, ChannelProviderViewModel> {

    override suspend fun map(model: String): ChannelProviderViewModel = TODO()
}