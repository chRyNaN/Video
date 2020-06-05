package com.chrynan.video.parcel

import android.os.Parcel
import com.chrynan.video.model.ServiceProviderScreen
import kotlinx.android.parcel.Parceler

object ServiceProviderScreenParceler : Parceler<ServiceProviderScreen> {

    override fun create(parcel: Parcel): ServiceProviderScreen {
        val typeName = parcel.readString()
            ?: throw NoSuchElementException("type was not found in ${ServiceProviderScreenParceler::class.java.name}")


        return when (typeName) {
            ServiceProviderScreen.TYPE_LIST -> createList(parcel)
            ServiceProviderScreen.TYPE_NEW -> createNew(parcel)
            ServiceProviderScreen.TYPE_DETAILS -> createDetails(parcel)
            else -> throw NoSuchElementException("Failed to create ${ServiceProviderScreen::class.java.name} in ${ServiceProviderScreenParceler::class.java.name}")
        }
    }

    override fun ServiceProviderScreen.write(parcel: Parcel, flags: Int) {
        parcel.writeString(type)


        when (this) {
            is ServiceProviderScreen.List -> writeList(this, parcel)
            is ServiceProviderScreen.New -> writeNew(this, parcel)
            is ServiceProviderScreen.Details -> writeDetails(this, parcel)
        }
    }

    private fun createList(parcel: Parcel): ServiceProviderScreen.List = ServiceProviderScreen.List

    private fun createNew(parcel: Parcel): ServiceProviderScreen.New = ServiceProviderScreen.New

    private fun createDetails(parcel: Parcel): ServiceProviderScreen.Details =
        ServiceProviderScreen.Details(
            providerUri = parcel.readString()
                ?: throw NoSuchElementException("Failed to create ${ServiceProviderScreen.Details::class.java.name} in ${ServiceProviderScreenParceler::class.java.name}. No Provider Uri field found.")
        )

    private fun writeList(list: ServiceProviderScreen.List, parcel: Parcel) {}

    private fun writeNew(new: ServiceProviderScreen.New, parcel: Parcel) {}

    private fun writeDetails(details: ServiceProviderScreen.Details, parcel: Parcel) {
        parcel.writeString(details.providerUri)
    }
}