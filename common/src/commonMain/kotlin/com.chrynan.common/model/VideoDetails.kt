package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class VideoDetails(
    val isLive: Boolean,
    val initialDelay: Float? = null,
    val length: Float? = null,
    val formatType: FormatType,
    val containerFormat: ContainerFormat,
    val videoType: VideoType,
    val uri: UriString,
    val standaloneSubtitle: Subtitle? = null
)