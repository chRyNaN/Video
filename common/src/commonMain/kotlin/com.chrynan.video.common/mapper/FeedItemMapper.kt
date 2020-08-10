package com.chrynan.video.common.mapper

import com.chrynan.video.common.Inject
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.common.model.api.VideoInfo

class FeedItemMapper @Inject constructor() :
    Mapper<FeedQuery.Data, List<FeedItem>> {

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
                    previewImageUri = node.video.images.preview
                )

                FeedItem.VideoFeedItem(
                    videoInfo = videoInfo,
                    videoName = node.video.name,
                    videoDescription = node.video.description,
                    isVideoLive = false,
                    videoLengthInMilliseconds = node.video.duration.totalMilliseconds as? Long,
                    videoViewCount = node.video.count.totalViews as? Long,
                    videoImage = node.video.images.preview,
                    channelName = node.channel.name,
                    isSubscribedToChannel = node.channel.isSubscribed,
                    channelSubscriberCount = node.channel.count.totalSubscribers as Long,
                    channelImage = node.channel.images.thumbnail,
                    providerName = providerName
                )
            }
    }
}