package com.chrynan.common.model.api

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Moment
import com.chrynan.common.model.core.Node
import com.chrynan.common.model.core.TimeDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Viewer(
    @SerialName(value = "id") override val id: ID,
    @SerialName(value = "created") override val created: Moment,
    @SerialName(value = "lastUpdated") override val lastUpdated: Moment,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "isAuthenticated") val isAuthenticated: Boolean = false,
    @SerialName(value = "subscribedChannels") val subscribedChannels: ChannelConnection = ChannelConnection()
) : Node,
    TimeDetail