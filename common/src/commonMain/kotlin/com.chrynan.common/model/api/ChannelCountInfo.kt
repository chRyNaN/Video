package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelCountInfo(
    @SerialName(value = "totalSubscribers") val totalSubscribers: Int = 0,
    @SerialName(value = "totalVideoViews") val totalVideoViews: Int = 0
)