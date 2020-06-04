package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString

data class ProviderImageInfo(
    val thumbnail: UriString? = null,
    val banner: UriString? = null
)