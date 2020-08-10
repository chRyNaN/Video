package com.chrynan.video.common.mapper

import com.chrynan.video.common.Inject
import com.chrynan.video.common.model.api.VideoDetails
import com.chrynan.video.common.model.api.VideoInfo

class VideoDetailsMapper @Inject constructor() :
    Mapper<VideoDetailsQuery.Data, VideoDetails?> {

    override suspend fun map(model: VideoDetailsQuery.Data): VideoDetails? {
        if (model.result == null) return null

        val videoInfo = VideoInfo(
            videoId = model.result.video.id,
            videoUri = model.result.video.uri,
            previewImageUri = model.result.video.images.preview,
            channelId = model.result.channel.id,
            providerUri = model.provider.uri
        )

        return VideoDetails(
            videoInfo = videoInfo,
            data = model
        )
    }
}