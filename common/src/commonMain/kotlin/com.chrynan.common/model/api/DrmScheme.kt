package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DrmScheme {

    @SerialName(value = "CENC")
    CENC,

    @SerialName(value = "CBCS")
    CBCS,

    @SerialName(value = "CBC1")
    CBC1,

    @SerialName(value = "CENS")
    CENS,

    @SerialName(value = "CLEAR_KEY")
    CLEAR_KEY,

    @SerialName(value = "PLAYREAD_SL2000")
    PLAYREADY_SL2000
}