package com.chrynan.video.player

data class DrmInfo(
    val licenseServerUrl: String,
    val type: DrmType,
    val isMultiSession: Boolean,
    val offlineLicenseKeySetId: String? = null
)