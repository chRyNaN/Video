package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SubtitleFormat {

    @SerialName(value = "WEB_VTT")
    WEB_VTT,

    @SerialName(value = "TIML")
    TTML,

    @SerialName(value = "SUB_RIP")
    SUB_RIP,

    @SerialName(value = "SUB_STATION_ALPHA")
    SUB_STATION_ALPHA
}