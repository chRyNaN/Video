package com.chrynan.common.model.api

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfo(
    @SerialName(value = "type") val type: LoginType,
    @SerialName(value = "loginUri") val loginUri: UriString? = null,
    @SerialName(value = "requestId") val requestId: ID? = null
)