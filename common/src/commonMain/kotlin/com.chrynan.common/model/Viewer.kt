package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Moment
import com.chrynan.common.model.core.Node
import com.chrynan.common.model.core.TimeDetail

data class Viewer(
    override val id: ID,
    override val created: Moment,
    override val lastUpdated: Moment,
    val name: String,
    val email: String? = null,
    val username: String? = null,
    val namePreference: NamePreference = NamePreference.NAME,
    val isAuthenticated: Boolean = false,
    val subscribedChannels: ChannelConnection = ChannelConnection()
) : Node,
    TimeDetail