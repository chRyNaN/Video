package com.chrynan.common.model

data class TagSuggestion(
    val name: String,
    val nestedSuggestions: List<TagSuggestion> = emptyList()
)