package com.chrynan.video.mapper.channel

import com.chrynan.common.model.api.ChannelResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelHeaderViewModel
import javax.inject.Inject

class ChannelHeaderMapper @Inject constructor() : Mapper<ChannelResult, ChannelHeaderViewModel> {

    override suspend fun map(model: ChannelResult): ChannelHeaderViewModel =
        ChannelHeaderViewModel(
            channelId = model.channel.id,
            providerUri = model.provider.uri,
            name = model.channel.name,
            subscriberCount = model.channel.count.totalSubscribers.toString(), // TODO update
            totalVideoViewCount = model.channel.count.totalVideoViews.toString(), // TODO update
            channelHeaderImage = model.channel.images.banner,
            channelImage = model.channel.images.thumbnail
        )
}