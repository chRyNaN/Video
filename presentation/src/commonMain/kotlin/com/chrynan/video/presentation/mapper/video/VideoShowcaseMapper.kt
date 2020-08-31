package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.presentation.core.Mapper
import com.chrynan.video.presentation.viewmodel.VideoShowcaseViewModel

class VideoShowcaseMapper @Inject constructor() :
    Mapper<FeedItem<FeedQuery.AsVideoFeedItem>, VideoShowcaseViewModel> {

    override suspend fun map(model: FeedItem<FeedQuery.AsVideoFeedItem>): VideoShowcaseViewModel =
        VideoShowcaseViewModel(
            providerUri = model.provider.uri,
            provider = model.provider.name,
            title = model.item.video.name,
            videoId = model.item.video.id,
            details = model.item.video.description ?: "",
            previewImage = model.item.video.images.preview,
            videoLength = model.item.video.duration.totalMilliseconds?.toString() ?: "",
            channelImageUrl = model.item.channel.images.thumbnail
        )
}