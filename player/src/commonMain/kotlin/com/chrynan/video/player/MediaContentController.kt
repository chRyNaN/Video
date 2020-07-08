package com.chrynan.video.player

interface MediaContentController {

    val isPlaying: Boolean

    val isPlayingAd: Boolean

    fun load(source: Playable)

    fun resume()

    fun pause()

    fun seekToDefaultPosition()
}