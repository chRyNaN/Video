package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class Subtitle(
    val uri: UriString,
    val subtitleFormat: SubtitleFormat
)