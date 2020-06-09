package com.chrynan.video.ui.view

import com.chrynan.video.viewmodel.TagItemViewModel

interface SearchView : View,
    ListView {

    fun updateTags(tags: List<TagItemViewModel>)
}