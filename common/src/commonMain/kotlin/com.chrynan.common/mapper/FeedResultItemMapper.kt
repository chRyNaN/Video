package com.chrynan.common.mapper

import com.chrynan.common.Inject
import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.api.VideoResult
import com.chrynan.common.model.response.FeedResponse
import com.chrynan.common.model.wrapper.FeedItemWrapper

class FeedResultItemMapper @Inject constructor() : Mapper<FeedResponse, List<FeedItemWrapper>> {

    override suspend fun map(model: FeedResponse): List<FeedItemWrapper> =
        model.connection.nodes.filterIsInstance<FeedItem.VideoFeedItem>()
            .map { item ->
                val videoInfo = VideoInfo(
                    videoId = item.video.id,
                    videoUri = item.video.uri,
                    channelId = item.channel.id,
                    providerUri = model.provider.uri,
                    previewImageUri = item.video.previewImage
                )

                val videoResult = VideoResult(
                    info = videoInfo,
                    video = item.video,
                    channel = item.channel,
                    provider = model.provider
                )

                FeedItemWrapper.Video(videoResult = videoResult)
            }
}