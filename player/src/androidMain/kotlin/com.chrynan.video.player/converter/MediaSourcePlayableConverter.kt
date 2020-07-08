package com.chrynan.video.player.converter

import android.net.Uri
import com.chrynan.video.player.DrmInfo
import com.chrynan.video.player.MediaSource
import com.chrynan.video.player.Playable
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.upstream.DataSource

class MediaSourcePlayableConverter(private val dataSourceFactory: DataSource.Factory) :
    BaseTypedPlayableConverter<MediaSource>() {

    override fun handlesPlayable(playable: Playable) = playable is MediaSource

    override fun handleConversion(playable: MediaSource): com.google.android.exoplayer2.source.MediaSource? =
        when (playable) {
            is MediaSource.Default ->
                ProgressiveMediaSource.Factory(dataSourceFactory)
                    .setTag(playable.tag)
                    .setDrmInfo(playable.drmInfo)
                    .createMediaSource(Uri.parse(playable.uri))
            is MediaSource.Dash ->
                DashMediaSource.Factory(dataSourceFactory)
                    .setTag(playable.tag)
                    .setDrmInfo(playable.drmInfo)
                    .createMediaSource(Uri.parse(playable.uri))
            is MediaSource.Hls ->
                HlsMediaSource.Factory(dataSourceFactory)
                    .setTag(playable.tag)
                    .setDrmInfo(playable.drmInfo)
                    .createMediaSource(Uri.parse(playable.uri))
            is MediaSource.SmoothStreaming ->
                SsMediaSource.Factory(dataSourceFactory)
                    .setTag(playable.tag)
                    .setDrmInfo(playable.drmInfo)
                    .createMediaSource(Uri.parse(playable.uri))
        }

    private fun ProgressiveMediaSource.Factory.setDrmInfo(drmInfo: DrmInfo?): ProgressiveMediaSource.Factory =
        this // TODO

    private fun DashMediaSource.Factory.setDrmInfo(drmInfo: DrmInfo?): DashMediaSource.Factory =
        this // TODO

    private fun HlsMediaSource.Factory.setDrmInfo(drmInfo: DrmInfo?): HlsMediaSource.Factory =
        this // TODO

    private fun SsMediaSource.Factory.setDrmInfo(drmInfo: DrmInfo?): SsMediaSource.Factory =
        this // TODO
}
