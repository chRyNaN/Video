package com.chrynan.video.common.model

data class AppInfo(
    val applicationId: String,
    val buildType: String,
    val version: String,
    val versionCode: Int? = null,
    val target: String
)