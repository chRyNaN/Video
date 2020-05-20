package com.chrynan.video.media

import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import javax.inject.Inject

class SimpleExoPlayerMediaController @Inject constructor(override val player: SimpleExoPlayer) :
    MediaController {

    override val isPlaying: Boolean
        get() = player.isPlaying

    private var previousVolumeValue = 0f

    override fun load(source: MediaSource) {
        player.prepare(source)
    }

    override fun resume() {
        player.playWhenReady = true
    }

    override fun pause() {
        player.playWhenReady = false
    }

    override fun mute() {
        val currentVolume = player.volume

        if (currentVolume != 0f) {
            previousVolumeValue = player.volume
            player.volume = 0f
        }
    }

    override fun unMute() {
        player.volume = previousVolumeValue
    }

    override fun release() {
        player.release()
    }
}