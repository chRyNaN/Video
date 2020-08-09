package com.chrynan.video.utils

import com.chrynan.video.presentation.viewmodel.TagItemViewModel
import javax.inject.Inject

class TagItemHandler @Inject constructor() {

    val selectedTags: Set<TagItemViewModel>
        get() = topLevelTags.filter { it.isSelected }.toSet()

    val unselectedTags: Set<TagItemViewModel>
        get() = topLevelTags.filter { !it.isSelected }.toSet()

    val allTags: Set<TagItemViewModel>
        get() = topLevelTags.toSet()

    private var topLevelTags = mutableListOf<TagItemViewModel>()

    fun updateTags(tags: List<TagItemViewModel>) {
        topLevelTags.clear()
        topLevelTags.addAll(tags)
    }

    fun selectTag(tag: TagItemViewModel) {
        val firstUnselectedItemIndex = topLevelTags.indexOfFirst { !it.isSelected }

        topLevelTags.remove(tag)

        val tagIsSelected = !tag.isSelected

        val index = when {
            tagIsSelected && firstUnselectedItemIndex == -1 -> 0
            !tagIsSelected && firstUnselectedItemIndex == -1 -> topLevelTags.size
            else -> firstUnselectedItemIndex
        }

        topLevelTags.add(index, tag.copy(isSelected = tagIsSelected))
    }
}