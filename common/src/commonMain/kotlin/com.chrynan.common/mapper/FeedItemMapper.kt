package com.chrynan.common.mapper

import com.chrynan.common.Inject
import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.model.api.VideoInfo

class FeedItemMapper @Inject constructor() : Mapper<FeedQuery.Data, List<FeedItem>> {

    override suspend fun map(model: FeedQuery.Data): List<FeedItem> {
        val providerUri = model.provider.uri
        val providerName = model.provider.name

        return model.feedItems.nodes
            .filterIsInstance<FeedQuery.AsVideoFeedItem>()
            .map { node ->
                val videoInfo = VideoInfo(
                    videoId = node.video.id,
                    channelId = node.channel.id,
                    providerUri = providerUri,
                    videoUri = node.video.uri,
                    previewImageUri = node.video.previewImage
                )

                FeedItem.VideoFeedItem(
                    videoInfo = videoInfo,
                    videoName = node.video.name,
                    videoDescription = node.video.description,
                    isVideoLive = node.video.isLive,
                    videoLengthInMilliseconds = node.video.lengthInMilliseconds.toLong(),
                    videoViewCount = node.video.viewCount?.toLong(),
                    videoImage = node.video.previewImage,
                    channelName = node.channel.name,
                    isSubscribedToChannel = node.channel.isSubscribed,
                    channelSubscriberCount = node.channel.count.totalSubscribers.toLong(),
                    channelImage = node.channel.images.thumbnail,
                    providerName = providerName
                )
            }
    }
}