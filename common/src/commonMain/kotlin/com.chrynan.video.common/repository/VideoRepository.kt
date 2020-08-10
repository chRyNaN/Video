package com.chrynan.video.common.repository

import com.chrynan.video.common.model.api.VideoDetails
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getVideoDetails(providerUri: UriString, videoId: ID): Flow<VideoDetails>
}
