package com.chrynan.presentation.viewmodel

interface UniqueListItem {

    val uniqueListId: Long
}

inline fun <reified T : Any> T.asUniqueListId() = hashCode().toLong()