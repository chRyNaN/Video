package com.chrynan.common.paginate

import com.chrynan.common.Inject
import com.chrynan.common.model.core.UriString

class MapCursorCache @Inject constructor() : CursorCache {

    private val cacheMap = mutableMapOf<UriString, CursorCacheValue?>()

    override fun set(key: UriString, value: CursorCacheValue?) {
        cacheMap[key] = value
    }

    override fun get(key: UriString): CursorCacheValue? = cacheMap[key]
}