package com.chrynan.video.parcel.model

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.parcel.VideoInfoParceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.WriteWith

@Parcelize
data class VideoInfoParcelWrapper(val videoInfo: @WriteWith<VideoInfoParceler> VideoInfo) :
    Parcelable

fun Intent.putVideoInfo(key: String, videoInfo: VideoInfo) =
    putExtra(key, VideoInfoParcelWrapper(videoInfo))

fun Intent.getVideoInfo(key: String) = getParcelableExtra<VideoInfoParcelWrapper>(key)?.videoInfo

fun Bundle.putVideoInfo(key: String, videoInfo: VideoInfo) =
    putParcelable(key, VideoInfoParcelWrapper(videoInfo))

fun Bundle.getVideoInfo(key: String) = getParcelable<VideoInfoParcelWrapper>(key)?.videoInfo