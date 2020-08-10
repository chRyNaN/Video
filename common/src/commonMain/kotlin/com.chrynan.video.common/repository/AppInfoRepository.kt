package com.chrynan.video.common.repository

import com.chrynan.video.common.model.AppInfo

interface AppInfoRepository {

    suspend fun getAppInfo(): AppInfo
}