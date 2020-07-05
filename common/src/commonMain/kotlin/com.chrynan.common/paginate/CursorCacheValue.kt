package com.chrynan.common.paginate

import com.chrynan.common.model.core.Cursor

data class CursorCacheValue(
    val cursor: Cursor? = null,
    val hasNextPage: Boolean = false
)