package com.chrynan.common.model

data class VideoDetails(
    val isLive: Boolean,
    val initialDelay: Float? = null,
    val length: Float? = null,
    val formatType: FormatType,
    val containerFormat: ContainerFormat,
    val videoType: VideoType,
    val uri: Uri,
    val standaloneSubtitle: Subtitle? = null
)