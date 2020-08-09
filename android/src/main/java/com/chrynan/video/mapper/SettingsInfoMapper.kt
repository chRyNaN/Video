package com.chrynan.video.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.SettingsInfo
import com.chrynan.video.R
import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.resources.ResourceAccessor
import com.chrynan.video.presentation.viewmodel.SettingsItemViewModel
import com.chrynan.video.presentation.viewmodel.builder.settings
import javax.inject.Inject

class SettingsInfoMapper @Inject constructor(
    resourceAccessor: ResourceAccessor
) : Mapper<SettingsInfo, List<AdapterItem>>,
    ResourceAccessor by resourceAccessor {

    private val groupTitleContent by string(R.string.settings_group_title_content)
    private val groupTitleAbout by string(R.string.settings_group_title_about)
    private val itemTitleServices by string(R.string.settings_item_title_services)
    private val itemTitleAddService by string(R.string.settings_item_title_add_service)
    private val itemTitleApp by string(R.string.settings_item_title_app)
    private val itemTitleSourceCode by string(R.string.settings_item_title_source_code)
    private val itemTitleLicense by string(R.string.settings_item_title_license)
    private val itemTitleOpenSourceLicenses by string(R.string.settings_item_title_open_source_licenses)

    override suspend fun map(model: SettingsInfo): List<AdapterItem> =
        settings {
            group(headerText = groupTitleContent) {
                +SettingsItemViewModel(
                    title = itemTitleServices,
                    type = SettingsItemViewModel.SettingsType.SERVICES,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = itemTitleAddService,
                    type = SettingsItemViewModel.SettingsType.ADD_SERVICE,
                    isSelectable = true
                )
            }
            group(headerText = groupTitleAbout) {
                +SettingsItemViewModel(
                    title = itemTitleApp,
                    description = model.appInfo.version,
                    type = SettingsItemViewModel.SettingsType.APP,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = itemTitleSourceCode,
                    type = SettingsItemViewModel.SettingsType.SOURCE_CODE,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = itemTitleLicense,
                    description = model.licenseName,
                    type = SettingsItemViewModel.SettingsType.LICENSE,
                    isSelectable = true
                )
                +SettingsItemViewModel(
                    title = itemTitleOpenSourceLicenses,
                    type = SettingsItemViewModel.SettingsType.OPEN_SOURCE_LICENSES,
                    isSelectable = true
                )
            }
        }
}