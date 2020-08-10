package com.chrynan.video.common.paginate

import com.chrynan.video.common.model.core.UriString

interface CursorCache {

    operator fun set(key: UriString, value: CursorCacheValue?)

    operator fun get(key: UriString): CursorCacheValue?
}