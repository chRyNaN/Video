package com.chrynan.video.player

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
sealed class MediaSourceEffect : Playable {

    abstract val source: MediaSource

    data class ClipStart internal constructor(
        override val source: MediaSource,
        val startPosition: Duration
    ) : MediaSourceEffect()

    data class ClipRange internal constructor(
        override val source: MediaSource,
        val startPosition: Duration,
        val endPosition: Duration
    ) : MediaSourceEffect()

    data class ClipDuration internal constructor(
        override val source: MediaSource,
        val duration: Duration
    ) : MediaSourceEffect()

    data class FiniteLoop internal constructor(
        override val source: MediaSource,
        val count: Int
    ) : MediaSourceEffect()
}
