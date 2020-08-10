package com.chrynan.video.common.repository.source

import VideoDetailsQuery
import com.chrynan.video.common.Inject
import com.chrynan.video.common.graphql.GraphQLClientFactory
import com.chrynan.video.common.graphql.filterSuccess
import com.chrynan.video.common.mapper.VideoDetailsMapper
import com.chrynan.video.common.model.api.VideoDetails
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import com.chrynan.video.common.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class VideoRepositorySource @Inject constructor(
    private val graphQLClientFactory: GraphQLClientFactory,
    private val videoDetailsMapper: VideoDetailsMapper
) : VideoRepository {

    override fun getVideoDetails(
        providerUri: UriString,
        videoId: ID
    ): Flow<VideoDetails> {
        val client = graphQLClientFactory.getOrCreate(providerUri)

        return client.query(VideoDetailsQuery(videoId = videoId))
            .filterSuccess()
            .mapNotNull { videoDetailsMapper.map(it) }
    }
}