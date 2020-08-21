package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.logger.Logger
import com.chrynan.video.R
import com.chrynan.video.common.utils.startWith
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.presentation.navigator.HomeScreen
import com.chrynan.video.presentation.navigator.ServiceProviderScreen
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.state.HomeState
import com.chrynan.video.presentation.presenter.HomePresenter
import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.ui.activity.ServiceProviderActivity
import com.chrynan.video.ui.adapter.factory.HomeAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.presentation.viewmodel.VideoShowcaseViewModel
import com.chrynan.video.ui.adapter.factory.calculateAndDispatchDiff
import com.chrynan.video.utils.loadMoreEvents
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.swiperefreshlayout.refreshes
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeIntent, HomeState, HomeChange, HomeScreen>(),
    VideoShowcaseAdapter.VideoShowcaseItemSelectedListener {

    companion object {

        fun newInstance() = HomeFragment()
    }

    @Inject
    override lateinit var presenter: HomePresenter

    @Inject
    lateinit var adapterFactory: HomeAdapterFactory

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    private val loadMoreIntents: Flow<HomeIntent>
        get() = homeRecyclerView.loadMoreEvents()
            .map { HomeIntent.LoadMore }

    private val refreshIntents: Flow<HomeIntent>
        get() = homeSwipeRefreshLayout.refreshes()
            .map { HomeIntent.Refresh }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeRecyclerView?.bindAdapterFactory(adapterFactory)

        homeAddServiceProviderButton?.setOnClickListener {
            startActivitySafely {
                ServiceProviderActivity.newIntent(
                    context = it,
                    screen = ServiceProviderScreen.New
                )
            }
        }
    }

    override fun intents(): Flow<HomeIntent> =
        merge(
            loadMoreIntents,
            refreshIntents
        ).startWith(HomeIntent.LoadInitial)

    override fun render(state: HomeState) {
        super.render(state)

        when (state) {
            is HomeState.DisplayingEmpty -> renderEmptyState()
            is HomeState.DisplayingLoaded -> renderLoadedState(state)
            is HomeState.LoadingInitial -> renderLoadingInitial()
            is HomeState.LoadingMore -> renderLoadingMore(state)
            is HomeState.Refreshing -> renderRefreshing(state)
        }
    }

    override fun goTo(screen: HomeScreen) =
        when (screen) {
            is HomeScreen.Video -> {
            }
        }

    override fun onVideoShowcaseItemSelected(item: VideoShowcaseViewModel) {

    }

    override fun onVideoShowcaseOptionsSelected(item: VideoShowcaseViewModel) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }

    private fun renderEmptyState() {
        homeRecyclerView?.visibility = View.GONE
        homeEmptyStateGroup?.visibility = View.VISIBLE
        homeSwipeRefreshLayout?.isRefreshing = false
    }

    private fun renderLoadedState(state: HomeState.DisplayingLoaded) {
        renderItems(state.items)
        homeSwipeRefreshLayout?.isRefreshing = false
    }

    private fun renderLoadingInitial() {
        homeRecyclerView?.visibility = View.VISIBLE
        homeEmptyStateGroup?.visibility = View.GONE
        homeSwipeRefreshLayout?.isRefreshing = true
    }

    private fun renderLoadingMore(state: HomeState.LoadingMore) {
        renderItems(state.currentItems)
        homeSwipeRefreshLayout?.isRefreshing = false
    }

    private fun renderRefreshing(state: HomeState.Refreshing) {
        renderItems(state.currentItems)
        homeSwipeRefreshLayout?.isRefreshing = true
    }

    private fun renderItems(items: List<AdapterItem>) {
        homeRecyclerView?.visibility = View.VISIBLE
        homeEmptyStateGroup?.visibility = View.GONE

        adapterFactory.calculateAndDispatchDiff(items)
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error loading items in HomeFragment. items = $items"
                )
            }
            .launchIn(this)
    }
}