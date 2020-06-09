package com.chrynan.common.mapper

import com.chrynan.common.Inject
import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.api.SearchResultItem
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.model.response.SearchResponse
import com.chrynan.common.model.wrapper.SearchResultItemWrapper

class SearchResultItemMapper @Inject constructor() :
    Mapper<SearchResponse, List<SearchResultItemWrapper>> {

    override suspend fun map(model: SearchResponse): List<SearchResultItemWrapper> =
        model.connection.nodes.map { node ->
            when (node) {
                is SearchResultItem.VideoSearchResultItem -> {
                    val videoInfo = VideoInfo(
                        videoId = node.video.id,
                        videoUri = node.video.uri,
                        channelId = node.channel.id,
                        providerUri = model.provider.uri,
                        previewImageUri = node.video.previewImage
                    )

                    val videoResult = VideoResult(
                        info = videoInfo,
                        video = node.video,
                        channel = node.channel,
                        provider = model.provider
                    )

                    SearchResultItemWrapper.Video(
                        videoResult = videoResult
                    )
                }
                is SearchResultItem.ChannelSearchResultItem -> {
                    val channelResult = ChannelResult(
                        channel = node.channel,
                        provider = model.provider
                    )

                    SearchResultItemWrapper.Channel(
                        channelResult = channelResult
                    )
                }
            }
        }
}