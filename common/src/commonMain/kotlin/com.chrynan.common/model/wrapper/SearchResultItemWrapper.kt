package com.chrynan.common.model.wrapper

import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.api.VideoResult

sealed class SearchResultItemWrapper {

    data class Video(
        val videoResult: VideoResult
    ) : SearchResultItemWrapper()

    data class Channel(
        val channelResult: ChannelResult
    ) : SearchResultItemWrapper()
}