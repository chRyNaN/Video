package com.chrynan.video.mapper.video

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.AdapterItem
import javax.inject.Inject

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