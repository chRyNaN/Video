package com.chrynan.common.repository

import com.chrynan.common.model.AppInfo

interface AppInfoRepository {

    suspend fun getAppInfo(): AppInfo
}