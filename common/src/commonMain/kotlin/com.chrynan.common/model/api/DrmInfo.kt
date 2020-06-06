package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrmInfo(
    @SerialName(value = "licenseServerUrl") val licenseServerUrl: UriString,
    @SerialName(value = "scheme") val scheme: DrmScheme,
    @SerialName(value = "multiSession") val multiSession: Boolean = false,
    @SerialName(value = "offlineLicenseKeySetId") val offlineLicenseKeySetId: String? = null
)