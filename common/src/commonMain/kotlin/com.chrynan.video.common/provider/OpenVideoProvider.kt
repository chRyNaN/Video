package com.chrynan.video.common.provider

import com.chrynan.video.common.model.api.VideoDetails
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import kotlinx.coroutines.flow.Flow

interface OpenVideoProvider {

    fun openFlow(providerUri: UriString, videoId: ID): Flow<VideoDetails>
}