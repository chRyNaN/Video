package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class ChannelImageInfo(
    val thumbnail: UriString? = null,
    val banner: UriString? = null
)