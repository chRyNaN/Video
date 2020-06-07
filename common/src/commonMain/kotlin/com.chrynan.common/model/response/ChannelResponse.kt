package com.chrynan.common.model.response

import com.chrynan.common.model.api.Provider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelResponse(
    @SerialName(value = "provider") val provider: Provider
)