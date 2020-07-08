package com.chrynan.video.player.converter

import com.chrynan.video.player.Playable
import com.google.android.exoplayer2.source.MediaSource

class DelegatePlayableConverter(private val converters: List<PlayableConverter> = emptyList()) :
    PlayableConverter {

    override fun handlesPlayable(playable: Playable) =
        converters.any { it.handlesPlayable(playable) }

    override fun convert(playable: Playable): MediaSource? {
        val converter = converters.firstOrNull { it.handlesPlayable(playable) }

        return converter?.convert(playable)
    }
}