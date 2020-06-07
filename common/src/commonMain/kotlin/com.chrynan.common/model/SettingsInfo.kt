package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

data class SettingsInfo(
    val appInfo: AppInfo,
    val sourceCodeUri: UriString,
    val licenseName: String,
    val licenseUri: UriString
)