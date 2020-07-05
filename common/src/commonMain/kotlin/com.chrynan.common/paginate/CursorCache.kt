package com.chrynan.common.paginate

import com.chrynan.common.model.core.UriString

interface CursorCache {

    operator fun set(key: UriString, value: CursorCacheValue?)

    operator fun get(key: UriString): CursorCacheValue?
}