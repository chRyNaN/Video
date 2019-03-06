package com.chrynan.video.ui.adapter.listener

import com.chrynan.video.model.VideoInfo

interface QueueOptionsListener {

    fun queueOptionsMenuSelected(videoInfo: VideoInfo)
}