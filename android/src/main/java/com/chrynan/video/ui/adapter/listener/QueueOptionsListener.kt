package com.chrynan.video.ui.adapter.listener

import com.chrynan.presentation.viewmodel.VideoInfo

interface QueueOptionsListener {

    fun queueOptionsMenuSelected(videoInfo: VideoInfo)
}