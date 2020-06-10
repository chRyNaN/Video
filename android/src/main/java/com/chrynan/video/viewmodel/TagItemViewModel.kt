package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId
import com.chrynan.video.ui.widget.ChipBackgroundColor

data class TagItemViewModel(
    val name: String,
    val isSelected: Boolean = false,
    val backgroundColor: ChipBackgroundColor = ChipBackgroundColor.ACCENT_ONE,
    val nestedTags: List<TagItemViewModel> = emptyList()
) : AdapterItem {

    override val uniqueAdapterId = "TagItem:$name".asUniqueAdapterId()
}