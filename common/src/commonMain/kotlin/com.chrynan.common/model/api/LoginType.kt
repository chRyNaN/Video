package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LoginType {

    @SerialName(value = "REQUIRED")
    REQUIRED,

    @SerialName(value = "OPTIONAL")
    OPTIONAL,

    @SerialName(value = "NONE")
    NONE
}