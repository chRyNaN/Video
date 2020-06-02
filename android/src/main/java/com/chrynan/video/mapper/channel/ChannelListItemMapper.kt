package com.chrynan.video.mapper.channel

import com.chrynan.common.model.ChannelResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import javax.inject.Inject

class ChannelListItemMapper @Inject constructor() :
    Mapper<ChannelResult, ChannelListItemViewModel> {

    override suspend fun map(model: ChannelResult): ChannelListItemViewModel =
        ChannelListItemViewModel(
            channelId = model.channel.id,
            providerUri = model.provider.uri,
            title = model.channel.name,
            description = model.channel.description ?: "", // TODO update
            channelImageUri = model.channel.images.thumbnail,
            isSubscribed = model.channel.isSubscribed
        )
}