package com.chrynan.video.presentation.mapper

import com.chrynan.common.Inject
import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.TagSuggestion
import com.chrynan.video.presentation.viewmodel.TagItemViewModel

class TagItemMapper @Inject constructor() : Mapper<TagSuggestion, TagItemViewModel> {

    override suspend fun map(model: TagSuggestion): TagItemViewModel =
        TagItemViewModel(
            name = model.name,
            isSelected = false,
            backgroundColor = 0
        )
}