package com.chrynan.video.ui.widget

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.chrynan.video.R
import com.chrynan.video.model.ColorInt
import com.chrynan.video.model.ResourceID
import com.google.android.material.chip.Chip

enum class ChipStyle(
    val isSingleSelection: Boolean,
    val isCheckable: Boolean
) {

    ENTRY(isSingleSelection = true, isCheckable = false),
    FILTER(isSingleSelection = false, isCheckable = true),
    CHOICE(isSingleSelection = true, isCheckable = true),
    ACTION(isSingleSelection = true, isCheckable = false)
}

enum class ChipBackgroundColor(val resourceID: ResourceID) {

    ACCENT_ONE(resourceID = R.color.bg_chip_item_one_color),
    ACCENT_TWO(resourceID = R.color.bg_chip_item_two_color),
    ACCENT_THREE(resourceID = R.color.bg_chip_item_three_color),
    PRIMARY(resourceID = R.color.bg_chip_item_primary_color)
}

fun chipOf(
    parent: ViewGroup,
    style: ChipStyle,
    text: String,
    backgroundColorStateList: ColorStateList? = null,
    textColor: ColorInt? = null
): Chip {
    val layoutResourceId = when (style) {
        ChipStyle.FILTER -> R.layout.layout_chip_filter
        ChipStyle.ACTION -> R.layout.layout_chip_action
        ChipStyle.CHOICE -> R.layout.layout_chip_choice
        ChipStyle.ENTRY -> R.layout.layout_chip_entry
    }

    return (LayoutInflater.from(parent.context)
        .inflate(layoutResourceId, parent, false) as Chip).apply {
        this.text = text

        if (backgroundColorStateList != null) {
            this.chipBackgroundColor = backgroundColorStateList
        }

        if (textColor != null) {
            this.setTextColor(textColor)
        }
    }
}

fun chipOf(
    parent: ViewGroup,
    style: ChipStyle,
    text: String,
    backgroundColor: ChipBackgroundColor
): Chip {
    val backgroundColorStateList =
        ContextCompat.getColorStateList(parent.context, backgroundColor.resourceID)
    val textColor = ContextCompat.getColor(parent.context, R.color.text_primary_color)

    return chipOf(
        parent = parent,
        style = style,
        text = text,
        backgroundColorStateList = backgroundColorStateList,
        textColor = textColor
    )
}

fun Chip.setChipBackgroundColor(backgroundColor: ChipBackgroundColor) {
    chipBackgroundColor = ContextCompat.getColorStateList(context, backgroundColor.resourceID)
}