package com.chrynan.video.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class VideoLoadType : Parcelable {

    @Parcelize
    data class ContentUri(val uri: Uri) : VideoLoadType()

    @Parcelize
    data class VideoUri(
        val providerUri: Uri,
        val videoId: String,
        val autoPlay: Boolean = true,
        val start: Long? = null,
        val end: Long? = null
    ) : VideoLoadType()

    @Parcelize
    data class LbryUri(val uri: Uri) :
        VideoLoadType() // TODO parameters should already be extracted
}