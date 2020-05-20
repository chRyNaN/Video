package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.FilterItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_filter_item.view.*
import javax.inject.Inject

@Adapter
class FilterItemAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: FilterItemCheckedListener
) : BaseAdapter<FilterItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is FilterItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_filter_item, parent, false)

    override fun View.onBindItem(item: FilterItemViewModel, position: Int) {
        filterItemChip?.let { chip ->
            chip.setChipBackgroundColorResource(item.backgroundColorResId)
            chip.text = item.name
            chip.isChecked = item.isChecked

            chip.setOnCheckedChangeListener { _, isChecked ->
                listener.onFilterItemCheckChange(isChecked = isChecked, filterItem = item)
                chip.isChecked = isChecked
            }
        }
    }

    interface FilterItemCheckedListener {

        fun onFilterItemCheckChange(isChecked: Boolean, filterItem: FilterItemViewModel)
    }
}