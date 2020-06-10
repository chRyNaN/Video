package com.chrynan.video.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.TagSuggestion
import com.chrynan.video.ui.widget.ChipBackgroundColor
import com.chrynan.video.viewmodel.TagItemViewModel
import javax.inject.Inject

class TagItemMapper @Inject constructor() : Mapper<TagSuggestion, TagItemViewModel> {

    override suspend fun map(model: TagSuggestion): TagItemViewModel {
        val colors = listOf(
            ChipBackgroundColor.ACCENT_ONE,
            ChipBackgroundColor.ACCENT_TWO,
            ChipBackgroundColor.ACCENT_THREE
        )

        return mapWithColor(model = model, colors = colors, count = 0)
    }

    private fun mapWithColor(
        model: TagSuggestion,
        colors: List<ChipBackgroundColor>,
        count: Int
    ): TagItemViewModel =
        TagItemViewModel(
            name = model.name,
            isSelected = false,
            backgroundColor = colors[count % colors.size],
            nestedTags = model.nestedSuggestions.map {
                mapWithColor(
                    model = it,
                    colors = colors,
                    count = count + 1
                )
            }
        )
}