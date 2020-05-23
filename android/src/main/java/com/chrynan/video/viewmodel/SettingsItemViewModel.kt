package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.model.ResourceID

data class SettingsItemViewModel(
    val title: String,
    val description: String? = null,
    val iconResourceID: ResourceID? = null,
    val endText: String? = null,
    val isSelectable: Boolean = false
) : AdapterItem {

    override val uniqueAdapterId = "SettingsItem:$title".asUniqueAdapterId()
}