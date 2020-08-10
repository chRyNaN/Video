package com.chrynan.video.presentation.mapper.channel

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.video.presentation.viewmodel.ChannelHeaderViewModel

class ChannelHeaderMapper @Inject constructor() :
    Mapper<String, ChannelHeaderViewModel> {

    override suspend fun map(model: String): ChannelHeaderViewModel = TODO()
}