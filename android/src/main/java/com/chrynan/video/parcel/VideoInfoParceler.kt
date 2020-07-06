package com.chrynan.video.parcel

import android.os.Parcel
import com.chrynan.common.model.api.VideoInfo
import kotlinx.android.parcel.Parceler

object VideoInfoParceler : Parceler<VideoInfo> {

    override fun create(parcel: Parcel): VideoInfo =
        VideoInfo(
            videoId = parcel.readString()
                ?: throw NoSuchElementException("Failed to create ${VideoInfo::class.java.name} in ${VideoInfoParceler::class.java.name}; no videoId field found."),
            videoUri = parcel.readString()
                ?: throw NoSuchElementException("Failed to create ${VideoInfo::class.java.name} in ${VideoInfoParceler::class.java.name}; no videoUri field found."),
            previewImageUri = parcel.readString(),
            channelId = parcel.readString()
                ?: throw NoSuchElementException("Failed to create ${VideoInfo::class.java.name} in ${VideoInfoParceler::class.java.name}; no channelId field found."),
            providerUri = parcel.readString()
                ?: throw NoSuchElementException("Failed to create ${VideoInfo::class.java.name} in ${VideoInfoParceler::class.java.name}; no providerUri field found.")
        )

    override fun VideoInfo.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this.videoId)
        parcel.writeString(this.videoUri)
        parcel.writeString(this.previewImageUri)
        parcel.writeString(this.channelId)
        parcel.writeString(this.providerUri)
    }
}