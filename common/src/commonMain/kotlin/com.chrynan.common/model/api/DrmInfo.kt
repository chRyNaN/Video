package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString

data class DrmInfo(
    val licenseServerUrl: UriString,
    val scheme: DrmScheme,
    val multiSession: Boolean = false,
    val offlineLicenseKeySetId: String? = null
)