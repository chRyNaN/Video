package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.SearchScreen
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import com.chrynan.video.presentation.state.SearchState
import com.chrynan.video.viewmodel.TagItemViewModel
import com.chrynan.video.presentation.presenter.SearchPresenter
import com.chrynan.video.ui.activity.VideoPlayerActivity
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.binder.SearchTagAdapterComponentsBinder
import com.chrynan.video.ui.adapter.channel.ChannelListItemAdapter
import com.chrynan.video.ui.adapter.factory.SearchAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.utils.showKeyboard
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.widget_search.view.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchFragment : BaseFragment<SearchIntent, SearchState, SearchChange, SearchScreen>(),
    VideoRecommendationAdapter.VideoRecommendationItemSelectedListener,
    SearchTagItemAdapter.SearchTagItemSelectedListener,
    ChannelListItemAdapter.ChannelListItemSelectedListener {

    companion object {

        fun newInstance() = SearchFragment()
    }

    @Inject
    override lateinit var presenter: SearchPresenter

    @Inject
    lateinit var adapterFactory: SearchAdapterFactory

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

        searchResultsRecyclerView?.bindAdapterFactory(adapterFactory)

        searchWidget?.setupTagAdapter(tagAdapterComponentsBinder)

        searchWidget?.onEnterPressed {
            searchWidget?.text?.let { }
        }

        searchWidget?.searchWidgetEditText?.showKeyboard()
    }

    override fun intents(): Flow<SearchIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: SearchState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: SearchScreen) {
        TODO("Not yet implemented")
    }

    override fun onVideoRecommendationItemSelected(item: VideoRecommendationViewModel) =
        startActivitySafely {
            VideoPlayerActivity.newIntent(context = it, videoInfo = item.videoInfo)
        }

    override fun onVideoRecommendationOptionsSelected(item: VideoRecommendationViewModel) {
    }

    override fun onSearchTagItemSelected(item: TagItemViewModel) {
    }

    override fun onChannelListItemSelected(item: ChannelListItemViewModel) {

    }

    override fun onChannelSubscribeButtonSelected(
        item: ChannelListItemViewModel,
        isChecked: Boolean
    ) {

    }
}