package com.chrynan.video.ui.view

import com.chrynan.video.viewmodel.TagItemViewModel

interface SearchView : View,
    EmptyListStateView {

    fun updateTags(tags: Set<TagItemViewModel>)
}