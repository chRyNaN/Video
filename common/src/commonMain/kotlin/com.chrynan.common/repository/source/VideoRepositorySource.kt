package com.chrynan.common.repository.source

import VideoDetailsQuery
import com.chrynan.common.Inject
import com.chrynan.common.graphql.GraphQLClientFactory
import com.chrynan.common.graphql.filterSuccess
import com.chrynan.common.mapper.VideoDetailsMapper
import com.chrynan.common.model.api.VideoDetails
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.common.repository.VideoRepository
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