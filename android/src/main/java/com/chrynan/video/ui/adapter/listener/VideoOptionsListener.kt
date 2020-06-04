package com.chrynan.video.ui.adapter.listener

import com.chrynan.common.model.api.VideoInfo

interface VideoOptionsListener {

    fun videoOptionsMenuSelected(videoInfo: VideoInfo)
}