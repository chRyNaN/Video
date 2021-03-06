package com.chrynan.video.provider.source

import com.chrynan.video.common.model.api.VideoDetails
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.common.repository.VideoRepository
import com.chrynan.video.common.utils.SharedFlow
import com.chrynan.video.common.utils.shareIn
import com.chrynan.video.common.provider.OpenVideoProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OpenVideoProviderSource(
    private val videoRepository: VideoRepository,
    private val coroutineScope: CoroutineScope
) : OpenVideoProvider {

    private val flowCache = mutableMapOf<CacheKey, SharedFlow<VideoDetails>>()

    override fun openFlow(providerUri: UriString, videoId: ID): Flow<VideoDetails> {
        val key = CacheKey(providerUri = providerUri, videoId = videoId)

        var sharedFlow = flowCache[key]

        if (sharedFlow == null) {
            sharedFlow =
                videoRepository.getVideoDetails(providerUri = providerUri, videoId = videoId)
                    .shareIn(coroutineScope)

            flowCache[key] = sharedFlow
        }

        return sharedFlow.openSubscription()
    }

    private data class CacheKey(
        val providerUri: UriString,
        val videoId: ID
    )
}