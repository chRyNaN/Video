package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.SettingsInfoRepository
import com.chrynan.common.utils.flowFrom
import com.chrynan.logger.Logger
import com.chrynan.video.mapper.SettingsInfoMapper
import com.chrynan.video.ui.adapter.factory.SettingsAdapterFactory
import com.chrynan.video.ui.adapter.factory.calculateAndDispatchDiff
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val settingsInfoRepository: SettingsInfoRepository,
    private val mapper: SettingsInfoMapper,
    private val adapterFactory: SettingsAdapterFactory
) : BasePresenter(dispatchers) {

    fun getSettings() {
        flowFrom { settingsInfoRepository.getSettingsInfo() }
            .map(mapper::map)
            .calculateAndDispatchDiff(adapterFactory)
            .catch { Logger.logError(throwable = it, message = "Error fetching Settings Info.") }
            .launchIn(this)
    }
}