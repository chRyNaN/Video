package com.chrynan.common.model.core

data class PageInfo(
    val startCursor: Cursor? = null,
    val endCursor: Cursor? = null,
    val hasNextPage: Boolean = false,
    val hasPreviousPage: Boolean = false
)