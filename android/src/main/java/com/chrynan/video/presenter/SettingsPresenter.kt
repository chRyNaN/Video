package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.BuildConfig
import com.chrynan.video.di.qualifier.SettingsQualifier
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.SectionHeaderViewModel
import com.chrynan.video.viewmodel.SettingsItemViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class SettingsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    @SettingsQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun getSettings() {
        val items = listOf(
            SectionHeaderViewModel("About"),
            SettingsItemViewModel(title = "Open Source Licenses", isSelectable = true),
            SettingsItemViewModel(title = "App Version", description = BuildConfig.VERSION_NAME)
        )

        flowOf(items)
            .calculateAndDispatchDiff(adapterItemHandler)
            .launchIn(this)
    }
}