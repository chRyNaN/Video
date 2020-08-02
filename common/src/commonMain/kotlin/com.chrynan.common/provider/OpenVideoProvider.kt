package com.chrynan.common.provider

import com.chrynan.common.model.api.VideoDetails
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import kotlinx.coroutines.flow.Flow

interface OpenVideoProvider {

    fun openFlow(providerUri: UriString, videoId: ID): Flow<VideoDetails>
}