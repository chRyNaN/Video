package com.chrynan.video.player

interface MediaController : MediaContentController,
    MediaVolumeController {

    fun attach(view: MediaPlayerView)

    fun detach()

    fun release()
}