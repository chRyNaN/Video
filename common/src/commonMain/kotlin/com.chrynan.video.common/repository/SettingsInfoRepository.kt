package com.chrynan.video.common.repository

import com.chrynan.video.common.model.SettingsInfo

interface SettingsInfoRepository {

    suspend fun getSettingsInfo(): SettingsInfo
}