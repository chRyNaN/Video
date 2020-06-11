package com.chrynan.common.repository

import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString

interface ChannelRepository {

    suspend fun getById(providerUri: UriString, channelId: ID): ChannelResult?
}