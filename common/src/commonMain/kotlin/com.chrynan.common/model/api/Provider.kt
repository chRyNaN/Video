package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Provider(
    @SerialName(value = "uri") val uri: UriString,
    @SerialName(value = "created") val created: String,
    @SerialName(value = "lastUpdated") val lastUpdated: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "about") val about: String? = null,
    @SerialName(value = "website") val website: UriString? = null,
    @SerialName(value = "contactEmail") val contactEmail: String? = null,
    @SerialName(value = "images") val images: ProviderImageInfo = ProviderImageInfo(),
    @SerialName(value = "channels") val channels: ChannelConnection = ChannelConnection(),
    @SerialName(value = "channel") val channel: Channel? = null
)