package com.chrynan.video.mapper.channel

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelProviderViewModel
import javax.inject.Inject

class ChannelProviderMapper @Inject constructor() :
    Mapper<String, ChannelProviderViewModel> {

    override suspend fun map(model: String): ChannelProviderViewModel = TODO()
}