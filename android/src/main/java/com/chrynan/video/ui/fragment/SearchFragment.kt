package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.viewmodel.TagItemViewModel
import com.chrynan.video.presenter.SearchPresenter
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.binder.SearchTagAdapterComponentsBinder
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView,
    VideoOptionsListener,
    SearchTagItemAdapter.SearchTagItemSelectedListener {

    companion object {

        fun newInstance() = SearchFragment()
    }

    @Inject
    override lateinit var presenter: SearchPresenter

    @Inject
    @field:SearchQualifier.Adapter
    lateinit var resultAdapter: RecyclerViewAdapter

    @Inject
    @field:SearchQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var tagAdapterComponentsBinder: SearchTagAdapterComponentsBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchWidget?.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                searchWidget?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                searchResultsRecyclerView?.apply {
                    setPadding(
                        paddingLeft,
                        searchWidget?.height ?: paddingTop,
                        paddingRight,
                        paddingBottom
                    )
                    smoothScrollToPosition(0)
                }
            }
        })

        searchResultsRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = resultAdapter
        }

        searchWidget?.setupTagAdapter(tagAdapterComponentsBinder)

        searchWidget?.onEnterPressed {
            searchWidget?.text?.let { presenter.handleQuery(query = it) }
        }

        presenter.loadInitialData()
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }

    override fun showEmptyState() {
        searchResultsRecyclerView?.visibility = View.GONE
    }

    override fun showListState() {
        searchResultsRecyclerView?.visibility = View.VISIBLE
    }

    override fun updateTags(tags: List<TagItemViewModel>) {
        searchWidget?.updateTags(tags)
    }

    override fun onSearchTagItemSelected(item: TagItemViewModel) {
        presenter.handleTagItemSelected(item)
    }
}