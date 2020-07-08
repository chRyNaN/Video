package com.chrynan.video.player.converter

import com.chrynan.video.player.Playable
import com.google.android.exoplayer2.source.MediaSource

abstract class BaseTypedPlayableConverter<P : Playable> : PlayableConverter {

    abstract fun handleConversion(playable: P): MediaSource?

    @Suppress("UNCHECKED_CAST")
    override fun convert(playable: Playable): MediaSource? {
        val typedPlayable = try {
            playable as? P
        } catch (throwable: Throwable) {
            null
        }

        return typedPlayable?.let { handleConversion(it) }
    }
}