package com.chrynan.video.mapper.channel

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelHeaderViewModel
import javax.inject.Inject

class ChannelHeaderMapper @Inject constructor() :
    Mapper<String, ChannelHeaderViewModel> {

    override suspend fun map(model: String): ChannelHeaderViewModel = TODO()
}