package com.chrynan.common.repository

import com.chrynan.common.model.SettingsInfo

interface SettingsInfoRepository {

    suspend fun getSettingsInfo(): SettingsInfo
}