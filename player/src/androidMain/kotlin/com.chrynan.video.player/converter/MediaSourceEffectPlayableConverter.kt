package com.chrynan.video.player.converter

import com.chrynan.video.player.MediaSourceEffect
import com.chrynan.video.player.Playable
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.source.ClippingMediaSource
import com.google.android.exoplayer2.source.LoopingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class MediaSourceEffectPlayableConverter(private val mediaSourceConverter: MediaSourcePlayableConverter) : BaseTypedPlayableConverter<MediaSourceEffect>() {

    override fun handlesPlayable(playable: Playable) = playable is MediaSourceEffect

    override fun handleConversion(playable: MediaSourceEffect): MediaSource? =
        when (playable) {
            is MediaSourceEffect.FiniteLoop -> LoopingMediaSource(
                mediaSourceConverter.convert(
                    playable.source
                ), playable.count
            )
            is MediaSourceEffect.ClipRange -> ClippingMediaSource(
                mediaSourceConverter.convert(
                    playable.source
                ),
                playable.startPosition.inMicroseconds.toLong(),
                playable.endPosition.inMicroseconds.toLong()
            )
            is MediaSourceEffect.ClipStart -> ClippingMediaSource(
                mediaSourceConverter.convert(
                    playable.source
                ), playable.startPosition.inMicroseconds.toLong(), C.TIME_END_OF_SOURCE
            )
            is MediaSourceEffect.ClipDuration -> ClippingMediaSource(
                mediaSourceConverter.convert(
                    playable.source
                ), playable.duration.inMicroseconds.toLong()
            )
        }
}