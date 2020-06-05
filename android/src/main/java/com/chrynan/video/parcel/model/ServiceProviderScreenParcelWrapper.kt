package com.chrynan.video.parcel.model

import android.content.Intent
import android.os.Parcelable
import com.chrynan.video.model.ServiceProviderScreen
import com.chrynan.video.parcel.ServiceProviderScreenParceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.WriteWith

@Parcelize
data class ServiceProviderScreenParcelWrapper(val screen: @WriteWith<ServiceProviderScreenParceler> ServiceProviderScreen) :
    Parcelable

fun Intent.putScreen(key: String, screen: ServiceProviderScreen) =
    putExtra(key, ServiceProviderScreenParcelWrapper(screen))

fun Intent.getScreen(key: String) =
    getParcelableExtra<ServiceProviderScreenParcelWrapper>(key)?.screen