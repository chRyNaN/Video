package com.chrynan.video.player

interface MediaVolumeController {

    companion object {

        const val MIN_VOLUME = 0.0f
        const val MAX_VOLUME = 1.0f
        const val MUTED_VOLUME = MIN_VOLUME
    }

    var volume: Float

    val isMuted: Boolean

    fun mute()

    fun unmute()
}