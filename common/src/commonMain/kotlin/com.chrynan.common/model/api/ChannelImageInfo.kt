package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelImageInfo(
    @SerialName(value = "thumbnail") val thumbnail: UriString? = null,
    @SerialName(value = "banner") val banner: UriString? = null
)