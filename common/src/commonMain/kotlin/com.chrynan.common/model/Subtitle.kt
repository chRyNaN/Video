package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class Subtitle(
    val locale: String,
    val uri: UriString,
    val format: SubtitleFormat
)