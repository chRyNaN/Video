package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.common.mapper.Mapper
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.presentation.viewmodel.VideoShowcaseViewModel

class VideoShowcaseMapper @Inject constructor() :
    Mapper<FeedItem.VideoFeedItem, VideoShowcaseViewModel> {

    override suspend fun map(model: FeedItem.VideoFeedItem): VideoShowcaseViewModel =
        VideoShowcaseViewModel(
            title = model.videoName,
            providerUri = model.videoInfo.providerUri,
            videoId = model.videoInfo.videoId,
            details = model.videoDescription ?: "",
            previewImage = model.videoInfo.previewImageUri,
            provider = model.providerName,
            videoLength = model.videoLengthInMilliseconds?.toString() ?: "",
            channelImageUrl = model.channelImage
        )
}