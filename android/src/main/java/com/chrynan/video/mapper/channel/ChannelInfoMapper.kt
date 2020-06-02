package com.chrynan.video.mapper.channel

import com.chrynan.common.model.ChannelResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelInfoViewModel
import javax.inject.Inject

class ChannelInfoMapper @Inject constructor() : Mapper<ChannelResult, ChannelInfoViewModel> {

    override suspend fun map(model: ChannelResult): ChannelInfoViewModel =
        ChannelInfoViewModel(
            about = model.channel.about,
            created = model.channel.created, // TODO update
            lastUpdated = model.channel.lastUpdated, // TODO update
            headerImageUri = model.channel.images.banner,
            channelImageUri = model.channel.images.thumbnail,
            isSubscribed = model.channel.isSubscribed,
            channelId = model.channel.id,
            providerUri = model.provider.uri,
            channelUrl = model.channel.website
        )
}