package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelResult(
    @SerialName(value = "channel") val channel: Channel,
    @SerialName(value = "provider") val provider: Provider
)