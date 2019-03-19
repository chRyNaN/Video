package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.presentation.viewmodel.FilterItemViewModel
import com.chrynan.video.R
import kotlinx.android.synthetic.main.adapter_filter_item.view.*
import javax.inject.Inject

class FilterItemAdapter @Inject constructor(private val listener: FilterItemCheckedListener) :
    AnotherAdapter<FilterItemViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is FilterItemViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_filter_item, parent, false)

    override fun onBindItem(view: View, item: FilterItemViewModel) {
        view.apply {
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
    }

    interface FilterItemCheckedListener {

        fun onFilterItemCheckChange(isChecked: Boolean, filterItem: FilterItemViewModel)
    }
}