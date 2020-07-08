package com.chrynan.video.player.converter

import com.chrynan.video.player.Playable
import com.google.android.exoplayer2.source.MediaSource

interface PlayableConverter {

    fun handlesPlayable(playable: Playable): Boolean

    fun convert(playable: Playable): MediaSource?
}