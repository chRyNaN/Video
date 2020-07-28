package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.model.api.VideoAction
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.VideoPlayerQualifier
import com.chrynan.video.presenter.VideoDetailsPresenter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.video.VideoInfoActionAdapter
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.ui.view.VideoDetailsView
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import kotlinx.android.synthetic.main.fragment_video_details.*
import javax.inject.Inject

class VideoDetailsFragment : BaseFragment(),
    VideoDetailsView,
    VideoRecommendationAdapter.VideoRecommendationItemSelectedListener,
    VideoShowcaseAdapter.VideoShowcaseItemSelectedListener,
    VideoInfoActionAdapter.VideoActionSelectedListener {

    companion object {

        fun newInstance() = VideoDetailsFragment()
    }

    @Inject
    override lateinit var presenter: VideoDetailsPresenter

    @Inject
    @field:VideoPlayerQualifier.Adapter
    lateinit var adapter: RecyclerViewAdapter

    @Inject
    @field:VideoPlayerQualifier.LayoutManager
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    @field:VideoPlayerQualifier.Decorator
    lateinit var decorator: VideoPlayerListDecorator

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoDetailsRecyclerView?.adapter = adapter
        videoDetailsRecyclerView?.layoutManager = layoutManager
        videoDetailsRecyclerView?.addItemDecoration(decorator)
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