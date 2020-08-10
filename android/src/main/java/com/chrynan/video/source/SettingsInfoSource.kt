package com.chrynan.video.source

import com.chrynan.video.common.model.SettingsInfo
import com.chrynan.video.common.repository.AppInfoRepository
import com.chrynan.video.common.repository.SettingsInfoRepository
import javax.inject.Inject

class SettingsInfoSource @Inject constructor(private val appInfoRepository: AppInfoRepository) :
    SettingsInfoRepository {

    override suspend fun getSettingsInfo(): SettingsInfo {
        val appInfo = appInfoRepository.getAppInfo()

        return SettingsInfo(
            appInfo = appInfo,
            sourceCodeUri = "", // TODO add these values and make them dynamic
            licenseName = "Apache 2.0",
            licenseUri = ""
        )
    }
}