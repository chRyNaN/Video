package com.chrynan.video.ui.adapter.listener

interface VideoOrderListener {

    fun playNowSelected(videoId: String, channelId: String, providerUrl: String)

    fun playNextSelected(videoId: String, channelId: String, providerUrl: String)

    fun addToQueueSelected(videoId: String, channelId: String, providerUrl: String)
}