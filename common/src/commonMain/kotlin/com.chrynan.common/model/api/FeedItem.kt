package com.chrynan.common.model.api

import kotlinx.serialization.Serializable

@Serializable
sealed class FeedItem {

    @Serializable
    class VideoFeedItem(

    ) : FeedItem()
}