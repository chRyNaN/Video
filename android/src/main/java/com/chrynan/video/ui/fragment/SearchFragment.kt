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
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.navigator.SearchNavigator
import com.chrynan.video.viewmodel.TagItemViewModel
import com.chrynan.video.presenter.SearchPresenter
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.binder.SearchTagAdapterComponentsBinder
import com.chrynan.video.ui.adapter.channel.ChannelListItemAdapter
import com.chrynan.video.utils.showKeyboard
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.widget_search.view.*
import javax.inject.Inject

class SearchFragment : BaseFragment(),
    SearchView,
    SearchNavigator,
    VideoOptionsListener,
    SearchTagItemAdapter.SearchTagItemSelectedListener,
    ChannelListItemAdapter.ChannelListItemSelectedListener {

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

        searchWidget?.searchWidgetEditText?.showKeyboard()

        presenter.loadInitialData()
    }

    override fun goToChannel(providerUri: UriString, channelId: ID) =
        goToFragment(ChannelFragment.newInstance(providerUri = providerUri, channelId = channelId))

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {

    }

    override fun showEmptyState() {
        searchResultsRecyclerView?.visibility = View.GONE
    }

    override fun showListState() {
        searchResultsRecyclerView?.visibility = View.VISIBLE
    }

    override fun updateTags(tags: Set<TagItemViewModel>) {
        searchWidget?.updateTags(tags)
    }

    override fun onSearchTagItemSelected(item: TagItemViewModel) {
        presenter.handleTagItemSelected(item)
    }

    override fun onChannelListItemSelected(item: ChannelListItemViewModel) =
        goToChannel(providerUri = item.providerUri, channelId = item.channelId)

    override fun onChannelSubscribeButtonSelected(
        item: ChannelListItemViewModel,
        isChecked: Boolean
    ) = presenter.handleSubscribeButtonSelected(item = item, isChecked = isChecked)
}