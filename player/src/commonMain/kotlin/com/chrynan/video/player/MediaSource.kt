package com.chrynan.video.player

sealed class MediaSource : Playable {

    companion object {

        fun from(
            uri: String,
            mediaType: MediaType = MediaType.DEFAULT,
            drmInfo: DrmInfo? = null,
            tag: String? = null
        ): MediaSource =
            when (mediaType) {
                MediaType.PROGRESSIVE -> Default(
                    uri = uri,
                    drmInfo = drmInfo,
                    tag = tag
                )
                MediaType.SMOOTH_STREAMING -> SmoothStreaming(
                    uri = uri,
                    drmInfo = drmInfo,
                    tag = tag
                )
                MediaType.HLS -> Hls(
                    uri = uri,
                    drmInfo = drmInfo,
                    tag = tag
                )
                MediaType.DASH -> Dash(
                    uri = uri,
                    drmInfo = drmInfo,
                    tag = tag
                )
            }
    }

    abstract val type: MediaType
    abstract val uri: String
    abstract val drmInfo: DrmInfo?
    abstract val tag: String?

    class Default internal constructor(
        override val uri: String,
        override val drmInfo: DrmInfo? = null,
        override val tag: String? = null
    ) : MediaSource() {

        override val type: MediaType = MediaType.DEFAULT
    }

    class Dash internal constructor(
        override val uri: String,
        override val drmInfo: DrmInfo? = null,
        override val tag: String? = null
    ) : MediaSource() {

        override val type: MediaType = MediaType.DASH
    }

    class Hls internal constructor(
        override val uri: String,
        override val drmInfo: DrmInfo? = null,
        override val tag: String? = null
    ) : MediaSource() {

        override val type: MediaType = MediaType.HLS
    }

    class SmoothStreaming internal constructor(
        override val uri: String,
        override val drmInfo: DrmInfo? = null,
        override val tag: String? = null
    ) : MediaSource() {

        override val type: MediaType = MediaType.SMOOTH_STREAMING
    }
}

fun mediaSource(
    uri: String,
    mediaType: MediaType = MediaType.DEFAULT,
    drmInfo: DrmInfo? = null,
    tag: String? = null
): MediaSource = MediaSource.from(
    uri = uri,
    mediaType = mediaType,
    drmInfo = drmInfo,
    tag = tag
)
