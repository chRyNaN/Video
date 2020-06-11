package com.chrynan.common.repository.source

import com.chrynan.common.Inject
import com.chrynan.common.api.ApiService
import com.chrynan.common.mapper.ChannelResultMapper
import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.common.repository.ChannelRepository
import com.chrynan.logger.Logger

class ChannelRepositorySource @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ChannelResultMapper
) : ChannelRepository {

    override suspend fun getById(providerUri: UriString, channelId: ID): ChannelResult? =
        try {
            val response =
                apiService.getChannelById(providerUri = providerUri, channelID = channelId)

            when {
                (response.isError || response.data == null) -> {
                    Logger.logError(message = "Error retrieving Channel with ID = $channelId from Provider with Uri = $providerUri. Errors = ${response.errors}")
                    null
                }
                response.data.provider.channel == null -> null
                else -> mapper.map(response.data)
            }
        } catch (throwable: Throwable) {
            Logger.logError(
                throwable = throwable,
                message = "Error retrieving Channel with ID = $channelId from Provider with Uri = $providerUri."
            )
            null
        }
}