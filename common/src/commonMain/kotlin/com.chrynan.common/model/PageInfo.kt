package com.chrynan.common.model

data class PageInfo(
    val isFirstPage: Boolean,
    val isLastPage: Boolean,
    val startCursor: Cursor? = null,
    val endCursor: Cursor? = null
)