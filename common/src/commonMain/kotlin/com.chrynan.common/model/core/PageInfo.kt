package com.chrynan.common.model.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    @SerialName(value = "startCursor") val startCursor: Cursor? = null,
    @SerialName(value = "endCursor") val endCursor: Cursor? = null,
    @SerialName(value = "hasNextPage") val hasNextPage: Boolean = false,
    @SerialName(value = "hasPreviousPage") val hasPreviousPage: Boolean = false
)