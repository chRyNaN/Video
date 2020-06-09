package com.chrynan.video.viewmodel

data class TagItemViewModel(
    val name: String,
    val isSelected: Boolean = false,
    val nestedTags: List<TagItemViewModel> = emptyList()
)