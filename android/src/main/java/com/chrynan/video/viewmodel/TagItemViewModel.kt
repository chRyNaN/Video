package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.R

data class TagItemViewModel(
    val name: String,
    val isSelected: Boolean = false,
    val backgroundColor: Int = R.color.bg_chip_item_one_color,
    val nestedTags: List<TagItemViewModel> = emptyList()
) : AdapterItem {

    override val uniqueAdapterId = "TagItem:$name".asUniqueAdapterId()
}