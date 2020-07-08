package com.chrynan.video.player

import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun MediaSource.clipStart(startPosition: Duration): MediaSourceEffect.ClipStart =
    MediaSourceEffect.ClipStart(source = this, startPosition = startPosition)

@OptIn(ExperimentalTime::class)
fun MediaSource.clipRange(
    startPosition: Duration,
    endPosition: Duration
): MediaSourceEffect.ClipRange =
    MediaSourceEffect.ClipRange(
        source = this,
        startPosition = startPosition,
        endPosition = endPosition
    )

@OptIn(ExperimentalTime::class)
fun MediaSource.clipDuration(duration: Duration): MediaSourceEffect.ClipDuration =
    MediaSourceEffect.ClipDuration(source = this, duration = duration)

fun MediaSource.loop(count: Int): MediaSourceEffect.FiniteLoop =
    MediaSourceEffect.FiniteLoop(source = this, count = count)