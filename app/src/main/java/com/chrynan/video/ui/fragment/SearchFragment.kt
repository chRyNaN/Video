package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.R
import com.chrynan.video.model.FilterItemViewModel
import com.chrynan.video.ui.adapter.FilterItemAdapter
import com.chrynan.video.ui.view.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView,
    FilterItemAdapter.FilterItemCheckedListener {

    companion object {

        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var filterItemAdapter: ManagerRecyclerViewAdapter<UniqueAdapterItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        filterItemRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterItemAdapter

            filterItemAdapter.items = listOf(
                FilterItemViewModel(
                    name = "Movie",
                    filterLevel = 0,
                    isChecked = false,
                    backgroundColorResId = R.color.chip_filter_item_one_color
                ),
                FilterItemViewModel(
                    name = "TV Series",
                    filterLevel = 0,
                    isChecked = false,
                    backgroundColorResId = R.color.chip_filter_item_two_color
                ),
                FilterItemViewModel(
                    name = "Video Clip",
                    filterLevel = 0,
                    isChecked = false,
                    backgroundColorResId = R.color.chip_filter_item_three_color
                ),
                FilterItemViewModel(
                    name = "Live",
                    filterLevel = 0,
                    isChecked = false,
                    backgroundColorResId = R.color.chip_filter_item_one_color
                ),
                FilterItemViewModel(
                    name = "Music Video",
                    filterLevel = 0,
                    isChecked = false,
                    backgroundColorResId = R.color.chip_filter_item_two_color
                )
            )
        }
    }

    override fun onFilterItemCheckChange(isChecked: Boolean, filterItem: FilterItemViewModel) {

    }
}