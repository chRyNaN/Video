package com.chrynan.video.common.model

data class SearchQuery(
    val query: String? = null,
    val selectedTags: Set<String> = emptySet()
)