package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.common.Inject
import com.chrynan.video.R
import com.chrynan.video.model.FilterItemViewModel
import com.chrynan.video.model.SectionHeaderViewModel
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.ui.adapter.FilterItemAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.view.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Named

class SearchFragment : BaseFragment(),
    SearchView,
    VideoOptionsListener,
    FilterItemAdapter.FilterItemCheckedListener {

    companion object {

        fun newInstance() = SearchFragment()
    }

    @Inject
    @field:Named("FilterItemAdapter")
    lateinit var filterItemAdapter: ManagerRecyclerViewAdapter<UniqueAdapterItem>

    @Inject
    @field:Named("ResultAdapter")
    lateinit var resultAdapter: ManagerRecyclerViewAdapter<UniqueAdapterItem>

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

        searchResultsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = resultAdapter

            resultAdapter.items = listOf(
                SectionHeaderViewModel(header = "Results")
            )
        }
    }

    override fun onFilterItemCheckChange(isChecked: Boolean, filterItem: FilterItemViewModel) {

    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }
}