package com.chrynan.common.repository

import com.chrynan.common.model.api.VideoDetails
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getVideoDetails(providerUri: UriString, videoId: ID): Flow<VideoDetails>
}
