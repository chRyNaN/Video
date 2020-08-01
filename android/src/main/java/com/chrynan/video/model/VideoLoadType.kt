package com.chrynan.video.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class VideoLoadType : Parcelable {

    @Parcelize
    data class ContentUri(val uri: Uri) : VideoLoadType()
}