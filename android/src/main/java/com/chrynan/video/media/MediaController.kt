package com.chrynan.video.media

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource

interface MediaController {

    val player: Player

    val isPlaying: Boolean

    fun load(source: MediaSource)

    fun resume()

    fun pause()

    fun mute()

    fun unMute()

    fun release()
}