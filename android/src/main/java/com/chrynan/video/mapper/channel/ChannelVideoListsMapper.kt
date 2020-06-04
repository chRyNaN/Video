package com.chrynan.video.mapper.channel

import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelVideoListViewModel
import javax.inject.Inject

class ChannelVideoListsMapper @Inject constructor(
    private val itemMapper: ChannelVideoListItemMapper
) : Mapper<ChannelResult, List<ChannelVideoListViewModel>> {

    override suspend fun map(model: ChannelResult): List<ChannelVideoListViewModel> =
        model.channel.lists.map { channelList ->
            ChannelVideoListViewModel(
                channelId = model.channel.id,
                providerUri = model.provider.uri,
                listName = channelList.name,
                items = channelList.nodes.map { video ->
                    val info = VideoInfo(
                        videoId = video.id,
                        providerUri = model.provider.uri,
                        channelId = model.channel.id,
                        videoUri = video.uri,
                        previewImageUri = video.previewImage
                    )
                    val videoResult = VideoResult(
                        info = info,
                        video = video,
                        channel = model.channel,
                        provider = model.provider
                    )

                    itemMapper.map(videoResult)
                }
            )
        }
}