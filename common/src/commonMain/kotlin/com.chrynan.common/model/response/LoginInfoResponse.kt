package com.chrynan.common.model.response

import com.chrynan.common.model.api.LoginInfo
import com.chrynan.common.model.api.Provider
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginInfoResponse(
    @SerialName(value = "apiVersion") val apiVersion: String,
    @SerialName(value = "login") val loginInfo: LoginInfo,
    @SerialName(value = "provider") val provider: Provider
)