package com.chrynan.common.mapper

import com.chrynan.common.Inject
import com.chrynan.common.model.api.ChannelResult
import com.chrynan.common.model.response.ChannelResponse

class ChannelResultMapper @Inject constructor() : Mapper<ChannelResponse, ChannelResult> {

    override suspend fun map(model: ChannelResponse): ChannelResult =
        ChannelResult(
            provider = model.provider,
            channel = model.provider.channel!!
        )
}