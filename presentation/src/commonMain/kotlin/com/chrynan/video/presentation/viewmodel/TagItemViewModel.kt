package com.chrynan.video.presentation.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class TagItemViewModel(
    val name: String,
    val isSelected: Boolean = false,
    val backgroundColor: Int
) : AdapterItem {

    override val uniqueAdapterId = "TagItem:$name".asUniqueAdapterId()
}