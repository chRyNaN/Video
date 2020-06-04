package com.chrynan.video.mapper.channel

import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelProviderViewModel
import javax.inject.Inject

class ChannelProviderMapper @Inject constructor() :
    Mapper<ChannelResult, ChannelProviderViewModel> {

    override suspend fun map(model: ChannelResult): ChannelProviderViewModel =
        ChannelProviderViewModel(
            channelId = model.channel.id,
            providerUri = model.provider.uri,
            providerServiceName = model.provider.name
        )
}