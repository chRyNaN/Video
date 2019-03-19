package com.chrynan.presentation.viewmodel

data class SectionHeaderViewModel(val header: String) : UniqueListItem {

    override val uniqueListId = "SectionHeader:$header".asUniqueListId()
}