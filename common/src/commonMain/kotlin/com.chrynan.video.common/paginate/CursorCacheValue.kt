package com.chrynan.video.common.paginate

import com.chrynan.video.common.model.core.Cursor

data class CursorCacheValue(
    val cursor: Cursor? = null,
    val hasNextPage: Boolean = false
)