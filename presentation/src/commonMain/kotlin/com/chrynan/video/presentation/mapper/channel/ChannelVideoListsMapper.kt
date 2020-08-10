package com.chrynan.video.presentation.mapper.channel

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelVideoListViewModel

class ChannelVideoListsMapper @Inject constructor(
    private val itemMapper: ChannelVideoListItemMapper
) : Mapper<String, List<ChannelVideoListViewModel>> {

    override suspend fun map(model: String): List<ChannelVideoListViewModel> = TODO()
}