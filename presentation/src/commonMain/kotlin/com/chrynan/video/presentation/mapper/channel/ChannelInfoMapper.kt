package com.chrynan.video.presentation.mapper.channel

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelInfoViewModel

class ChannelInfoMapper @Inject constructor() :
    Mapper<String, ChannelInfoViewModel> {

    override suspend fun map(model: String): ChannelInfoViewModel = TODO()
}