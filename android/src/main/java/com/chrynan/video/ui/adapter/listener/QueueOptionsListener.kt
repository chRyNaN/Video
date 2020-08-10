package com.chrynan.video.ui.adapter.listener

import com.chrynan.video.common.model.api.VideoInfo

interface QueueOptionsListener {

    fun queueOptionsMenuSelected(videoInfo: VideoInfo)
}