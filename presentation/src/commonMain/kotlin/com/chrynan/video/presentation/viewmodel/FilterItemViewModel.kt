package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.presentation.core.AdapterItem

data class FilterItemViewModel(
    val name: String,
    val filterLevel: Int,
    val isChecked: Boolean,
    val backgroundColorResId: Int
) : AdapterItem {

    override val uniqueAdapterId =
        "Filter Item: name = $name; filterLevel = $filterLevel; isChecked = $isChecked".asUniqueAdapterId()
}