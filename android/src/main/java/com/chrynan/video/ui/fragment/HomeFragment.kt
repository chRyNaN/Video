package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.presentation.navigator.HomeScreen
import com.chrynan.video.presentation.navigator.ServiceProviderScreen
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.state.HomeState
import com.chrynan.video.presentation.presenter.HomePresenter
import com.chrynan.video.ui.activity.ServiceProviderActivity
import com.chrynan.video.ui.adapter.factory.HomeAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.presentation.viewmodel.VideoShowcaseViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.Flow
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

    override fun intents(): Flow<HomeIntent> {

        TODO("Not yet implemented")
    }

    override fun render(state: HomeState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: HomeScreen) {
        TODO("Not yet implemented")
    }

    override fun onVideoShowcaseItemSelected(item: VideoShowcaseViewModel) {

    }

    override fun onVideoShowcaseOptionsSelected(item: VideoShowcaseViewModel) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}