package com.chrynan.video.model

import com.chrynan.video.common.model.core.UriString

sealed class ServiceProviderScreen {

    companion object {

        const val TYPE_LIST = "list"
        const val TYPE_NEW = "new"
        const val TYPE_DETAILS = "details"
    }

    abstract val type: String

    object List : ServiceProviderScreen() {

        override val type = TYPE_LIST
    }

    object New : ServiceProviderScreen() {

        override val type = TYPE_NEW
    }

    data class Details(val providerUri: UriString) : ServiceProviderScreen() {

        override val type = TYPE_DETAILS
    }
}