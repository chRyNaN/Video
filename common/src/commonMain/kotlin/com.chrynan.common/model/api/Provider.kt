package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString

data class Provider(
    val uri: UriString,
    val created: String,
    val lastUpdated: String,
    val name: String,
    val description: String? = null,
    val about: String? = null,
    val website: UriString? = null,
    val contactEmail: String? = null,
    val images: ProviderImageInfo = ProviderImageInfo(),
    val channels: ChannelConnection = ChannelConnection(),
    val channel: Channel? = null
)