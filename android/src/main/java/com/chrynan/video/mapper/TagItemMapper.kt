package com.chrynan.video.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.TagSuggestion
import com.chrynan.video.ui.widget.ChipBackgroundColor
import com.chrynan.video.viewmodel.TagItemViewModel
import javax.inject.Inject

class TagItemMapper @Inject constructor() : Mapper<TagSuggestion, TagItemViewModel> {

    override suspend fun map(model: TagSuggestion): TagItemViewModel =
        TagItemViewModel(
            name = model.name,
            isSelected = false,
            backgroundColor = ChipBackgroundColor.ACCENT_ONE.resourceID
        )
}