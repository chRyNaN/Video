package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.common.model.api.VideoAction
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.OpenVideoDetailsScreen
import com.chrynan.video.presentation.state.OpenVideoDetailsChange
import com.chrynan.video.presentation.state.OpenVideoDetailsIntent
import com.chrynan.video.presentation.state.OpenVideoDetailsState
import com.chrynan.video.presentation.presenter.OpenVideoDetailsPresenter
import com.chrynan.video.ui.adapter.factory.OpenVideoDetailsAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.video.VideoInfoActionAdapter
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.presentation.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.presentation.viewmodel.VideoShowcaseViewModel
import kotlinx.android.synthetic.main.fragment_open_video_details.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenVideoDetailsFragment :
    BaseFragment<OpenVideoDetailsIntent, OpenVideoDetailsState, OpenVideoDetailsChange, OpenVideoDetailsScreen>(),
    VideoRecommendationAdapter.VideoRecommendationItemSelectedListener,
    VideoShowcaseAdapter.VideoShowcaseItemSelectedListener,
    VideoInfoActionAdapter.VideoActionSelectedListener {

    companion object {

        fun newInstance() = OpenVideoDetailsFragment()
    }

    @Inject
    override lateinit var presenter: OpenVideoDetailsPresenter

    @Inject
    lateinit var adapterFactory: OpenVideoDetailsAdapterFactory

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_open_video_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openVideoDetailsRecyclerView?.bindAdapterFactory(adapterFactory)
    }

    override fun intents(): Flow<OpenVideoDetailsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: OpenVideoDetailsState) {
        super.render(state)

        TODO("Not yet implemented")
    }

    override fun goTo(screen: OpenVideoDetailsScreen) {
        TODO("Not yet implemented")
    }

    override fun onVideoRecommendationItemSelected(item: VideoRecommendationViewModel) {

    }

    override fun onVideoRecommendationOptionsSelected(item: VideoRecommendationViewModel) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }

    override fun onVideoShowcaseItemSelected(item: VideoShowcaseViewModel) {

    }

    override fun onVideoShowcaseOptionsSelected(item: VideoShowcaseViewModel) {
    }

    override fun onVideoActionSelected(action: VideoAction) {

    }
}