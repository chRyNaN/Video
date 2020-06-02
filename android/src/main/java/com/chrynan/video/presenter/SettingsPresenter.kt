package com.chrynan.video.presenter

import android.content.Context
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.BuildConfig
import com.chrynan.video.R
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
            SectionHeaderViewModel(header = "Content"),
            SettingsItemViewModel(
                title = "Services",
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.SERVICES
            ),
            SettingsItemViewModel(
                title = "Add Service",
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.ADD_SERVICE
            ),
            SectionHeaderViewModel(header = "About"),
            SettingsItemViewModel(
                title = "App Version",
                description = BuildConfig.VERSION_NAME,
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.APP
            ),
            SettingsItemViewModel(
                title = "Source Code",
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.SOURCE_CODE
            ),
            SettingsItemViewModel(
                title = "License",
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.LICENSE
            ),
            SettingsItemViewModel(
                title = "Open Source Licenses",
                isSelectable = true,
                type = SettingsItemViewModel.SettingsType.OPEN_SOURCE_LICENSES
            )
        )

        flowOf(items)
            .calculateAndDispatchDiff(adapterItemHandler)
            .launchIn(this)
    }
}