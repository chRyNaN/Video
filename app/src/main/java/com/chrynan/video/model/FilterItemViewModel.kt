package com.chrynan.video.model

import androidx.annotation.ColorRes
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class FilterItemViewModel(
    val name: String,
    val filterLevel: Int,
    val isChecked: Boolean,
    @ColorRes val backgroundColorResId: Int
) : UniqueAdapterItem {

    override val uniqueAdapterId =
        "Filter Item: name = $name; filterLevel = $filterLevel; isChecked = $isChecked".asUniqueAdapterId()
}