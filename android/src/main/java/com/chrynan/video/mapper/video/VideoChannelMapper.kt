package com.chrynan.video.mapper.video

import com.chrynan.common.model.VideoResult
import com.chrynan.video.mapper.Mapper
import com.chrynan.video.viewmodel.VideoInfoChannelViewModel
import javax.inject.Inject

class VideoChannelMapper @Inject constructor() :
    Mapper<VideoResult, VideoInfoChannelViewModel> {

    override suspend fun map(model: VideoResult): VideoInfoChannelViewModel =
        VideoInfoChannelViewModel(
            videoInfo = model.info,
            channelName = model.channel.name,
            channelImageUrl = model.channel.images.thumbnail,
            channelSubscriberCount = model.channel.count.totalSubscribers.toString(),
            isSubscribedToChannel = model.channel.isSubscribed
        )
}