package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.widget.ChipBackgroundColor
import com.chrynan.video.ui.widget.ChipStyle
import com.chrynan.video.ui.widget.chipOf
import com.chrynan.video.ui.widget.setChipBackgroundColor
import com.chrynan.video.viewmodel.TagItemViewModel
import com.google.android.material.chip.Chip
import javax.inject.Inject

@Adapter
class SearchTagItemAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<TagItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(SearchTagItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is TagItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = chipOf(
        parent = parent,
        text = "",
        style = ChipStyle.FILTER,
        backgroundColor = ChipBackgroundColor.ACCENT_ONE
    )

    override fun View.onBindItem(item: TagItemViewModel, position: Int) {
        val chip = (this as? Chip)

        chip?.text = item.name
        chip?.setChipBackgroundColor(item.backgroundColor)
    }
}