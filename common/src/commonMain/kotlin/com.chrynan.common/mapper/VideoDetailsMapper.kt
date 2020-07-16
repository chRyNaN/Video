package com.chrynan.common.mapper

import com.chrynan.common.Inject
import com.chrynan.common.model.api.VideoDetails
import com.chrynan.common.model.api.VideoInfo

class VideoDetailsMapper @Inject constructor() : Mapper<VideoDetailsQuery.Data, VideoDetails?> {

    override suspend fun map(model: VideoDetailsQuery.Data): VideoDetails? {
        if (model.provider.result == null) return null

        val videoInfo = VideoInfo(
            videoId = model.provider.result.video.id,
            videoUri = model.provider.result.video.uri,
            previewImageUri = model.provider.result.video.images.preview,
            channelId = model.provider.result.channel.id,
            providerUri = model.provider.uri
        )

        return VideoDetails(
            videoInfo = videoInfo,
            data = model
        )
    }
}