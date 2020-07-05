package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.api.FeedItem
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import javax.inject.Inject

class VideoShowcaseMapper @Inject constructor() :
    Mapper<FeedItem.VideoFeedItem, VideoShowcaseViewModel> {

    override suspend fun map(model: FeedItem.VideoFeedItem): VideoShowcaseViewModel = TODO()
}