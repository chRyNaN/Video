package com.chrynan.presentation.viewmodel

data class FilterItemViewModel(
    val name: String,
    val filterLevel: Int,
    val isChecked: Boolean,
    val backgroundColorResId: Int
) : UniqueListItem {

    override val uniqueListId =
        "Filter Item: name = $name; filterLevel = $filterLevel; isChecked = $isChecked".asUniqueListId()
}