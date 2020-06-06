package com.chrynan.common.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NamePreference {

    @SerialName(value = "NAME")
    NAME,

    @SerialName(value = "EMAIL")
    EMAIL,

    @SerialName(value = "USERNAME")
    USERNAME
}