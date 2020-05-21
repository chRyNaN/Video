package com.chrynan.video.ui.adapter.listener

import com.chrynan.video.viewmodel.VideoInfo

interface QueueOptionsListener {

    fun queueOptionsMenuSelected(videoInfo: VideoInfo)
}