package com.chrynan.video.media

import com.chrynan.common.model.core.UriString
import com.google.android.exoplayer2.source.MediaSource

interface MediaSourceCreator {

    fun fromUri(uri: UriString): MediaSource
}