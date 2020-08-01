package com.chrynan.video.player

import android.net.Uri

fun mediaSource(
    uri: Uri,
    mediaType: MediaType = MediaType.DEFAULT,
    drmInfo: DrmInfo? = null,
    tag: String? = null
): MediaSource = MediaSource.from(
    uri = uri.toString(),
    mediaType = mediaType,
    drmInfo = drmInfo,
    tag = tag
)