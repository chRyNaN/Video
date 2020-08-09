package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.presentation.model.ResourceID

data class SettingsItemViewModel(
    val title: String,
    val type: SettingsType,
    val description: String? = null,
    val iconResourceID: ResourceID? = null,
    val endText: String? = null,
    val isSelectable: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId = "SettingsItem:$title".asUniqueAdapterId()

    enum class SettingsType {

        SERVICES,
        ADD_SERVICE,
        APP,
        SOURCE_CODE,
        LICENSE,
        OPEN_SOURCE_LICENSES
    }
}