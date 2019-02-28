package com.chrynan.video.ui.adapter.listener

interface VideoOptionsListener : VideoOrderListener {

    fun dismissSelected(videoId: String, channelId: String, providerUrl: String)

    fun shareSelected(videoId: String, channelId: String, providerUrl: String)

    fun reportSelected(videoId: String, channelId: String, providerUrl: String)
}