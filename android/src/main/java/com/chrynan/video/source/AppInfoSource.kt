package com.chrynan.video.source

import com.chrynan.video.common.model.AppInfo
import com.chrynan.video.common.repository.AppInfoRepository
import com.chrynan.video.BuildConfig
import javax.inject.Inject

class AppInfoSource @Inject constructor() :
    AppInfoRepository {

    override suspend fun getAppInfo(): AppInfo =
        AppInfo(
            applicationId = BuildConfig.APPLICATION_ID,
            buildType = BuildConfig.BUILD_TYPE,
            version = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            target = "Android"
        )
}