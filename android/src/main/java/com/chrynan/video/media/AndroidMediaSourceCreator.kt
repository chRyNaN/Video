package com.chrynan.video.media

import android.content.Context
import android.net.Uri
import com.chrynan.common.model.core.UriString
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import javax.inject.Inject

class AndroidMediaSourceCreator @Inject constructor(context: Context) : MediaSourceCreator {

    private val dataSourceFactory: DataSource.Factory =
        DefaultDataSourceFactory(context, Util.getUserAgent(context, "Chat"))

    override fun fromUri(uri: UriString): MediaSource =
        ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(uri))
}