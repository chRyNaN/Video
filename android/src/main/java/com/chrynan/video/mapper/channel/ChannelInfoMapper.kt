package com.chrynan.video.mapper.channel

import com.chrynan.common.mapper.Mapper
import com.chrynan.video.viewmodel.ChannelInfoViewModel
import javax.inject.Inject

class ChannelInfoMapper @Inject constructor() :
    Mapper<String, ChannelInfoViewModel> {

    override suspend fun map(model: String): ChannelInfoViewModel = TODO()
}