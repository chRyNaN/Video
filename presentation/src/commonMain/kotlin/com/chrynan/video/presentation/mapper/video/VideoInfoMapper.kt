package com.chrynan.video.presentation.mapper.video

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.presentation.core.Mapper

class VideoInfoMapper @Inject constructor(
    private val headerMapper: VideoHeaderMapper,
    private val channelMapper: VideoChannelMapper,
    private val aboutMapper: VideoAboutMapper,
    private val detailsMapper: VideoDetailsMapper
) : Mapper<String, List<AdapterItem>> {

    override suspend fun map(model: String): List<AdapterItem> {
        val header = headerMapper.map(model)
        val channel = channelMapper.map(model)
        val about = aboutMapper.map(model)
        val details = detailsMapper.map(model)

        return listOf(header, channel, about, details)
    }
}