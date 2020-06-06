package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subtitle(
    @SerialName(value = "locale") val locale: String,
    @SerialName(value = "uri") val uri: UriString,
    @SerialName(value = "format") val format: SubtitleFormat
)