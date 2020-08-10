package com.chrynan.video.presentation.mapper

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.SettingsInfo
import com.chrynan.video.presentation.resources.Strings
import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.presentation.viewmodel.SettingsItemViewModel
import com.chrynan.video.presentation.viewmodel.builder.settings

class SettingsInfoMapper @Inject constructor(
    private val strings: Strings
) : Mapper<SettingsInfo, List<AdapterItem>> {

    override suspend fun map(model: SettingsInfo): List<AdapterItem> =
        settings {
            group(headerText = strings.settingsGroupTitleContent) {
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleServices,
                    type = SettingsItemViewModel.SettingsType.SERVICES,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleAddService,
                    type = SettingsItemViewModel.SettingsType.ADD_SERVICE,
                    isSelectable = true
                )
            }
            group(headerText = strings.settingsGroupTitleAbout) {
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleApp,
                    description = model.appInfo.version,
                    type = SettingsItemViewModel.SettingsType.APP,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleSourceCode,
                    type = SettingsItemViewModel.SettingsType.SOURCE_CODE,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleLicense,
                    description = model.licenseName,
                    type = SettingsItemViewModel.SettingsType.LICENSE,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = strings.settingsItemTitleOpenSourceLicenses,
                    type = SettingsItemViewModel.SettingsType.OPEN_SOURCE_LICENSES,
                    isSelectable = true
                )
            }
        }
}