package com.chrynan.video.player.converter

import com.chrynan.video.player.MediaSourceEffect
import com.chrynan.video.player.Playable
import com.chrynan.video.player.Playlist
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource

class PlaylistPlayableConverter(
    private val mediaSourcePlayableConverter: MediaSourcePlayableConverter,
    private val mediaSourceEffectPlayableConverter: MediaSourceEffectPlayableConverter
) : BaseTypedPlayableConverter<Playlist<*>>() {

    override fun handlesPlayable(playable: Playable) = playable is Playlist<*>

    override fun handleConversion(playable: Playlist<*>): MediaSource? {
        val sources = playable.mapNotNull {
            when (it) {
                is com.chrynan.video.player.MediaSource -> mediaSourcePlayableConverter.convert(it)
                is MediaSourceEffect -> mediaSourceEffectPlayableConverter.convert(it)
                is Playlist<*> -> convert(it)
                else -> null
            }
        }

        return ConcatenatingMediaSource(*sources.toTypedArray())
    }
}