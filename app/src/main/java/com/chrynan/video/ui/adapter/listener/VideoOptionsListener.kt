package com.chrynan.video.ui.adapter.listener

interface VideoOptionsListener : VideoOrderListener {

    fun videoOptionsMenuSelected(videoId: String, channelId: String, providerUrl: String) {}
}