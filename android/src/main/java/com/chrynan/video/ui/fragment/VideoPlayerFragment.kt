package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.expandable.ExpandableContainerView
import com.chrynan.presentation.presenter.VideoPlayerPresenter
import com.chrynan.presentation.view.CollapsibleVideoView
import com.chrynan.presentation.view.VideoPlayerView
import com.chrynan.presentation.viewmodel.SectionHeaderViewModel
import com.chrynan.presentation.viewmodel.VideoInfo
import com.chrynan.presentation.viewmodel.VideoInfoHeaderViewModel
import com.chrynan.presentation.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.ui.transition.CollapsingVideoTransitionStateListener
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment(),
    VideoPlayerView,
    CollapsibleVideoView,
    VideoOptionsListener {

    companion object {

        fun newInstance() = VideoPlayerFragment()
    }

    @Inject
    override lateinit var presenter: VideoPlayerPresenter

    @Inject
    lateinit var managerAdapter: RecyclerViewAdapter

    @Inject
    lateinit var transitionListener: CollapsingVideoTransitionStateListener

    @Inject
    lateinit var expandableView: ExpandableContainerView

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    override val containerWidth: Int
        get() = containerView?.measuredWidth ?: 0

    override val collapsedPlayIconWidth: Int
        get() = playIconImageView?.measuredWidth ?: 0

    override val collapsedCancelIconWidth: Int
        get() = cancelIconImageView?.measuredWidth ?: 0

    override var videoHeight: Int
        get() = videoPlayerView?.measuredHeight ?: 0
        set(value) {
            videoPlayerView?.layoutParams?.height = value
        }

    override var videoWidth: Int
        get() = videoPlayerView?.measuredWidth ?: 0
        set(value) {
            val params = videoPlayerView?.layoutParams as? ConstraintLayout.LayoutParams
            params?.width = value
            videoPlayerView?.layoutParams = params
        }

    override var collapsedPlayIconAlpha: Float
        get() = playIconImageView?.alpha ?: 0f
        set(value) {
            playIconImageView?.alpha = value
        }

    override var collapsedCancelIconAlpha: Float
        get() = cancelIconImageView?.alpha ?: 0f
        set(value) {
            cancelIconImageView?.alpha = value
        }

    override var collapsedPlayIconIsVisible: Boolean
        get() = playIconImageView?.visibility == View.VISIBLE
        set(value) {
            playIconImageView?.visibility = if (value) View.VISIBLE else View.GONE
        }

    override var collapsedCancelIconIsVisible: Boolean
        get() = cancelIconImageView?.visibility == View.VISIBLE
        set(value) {
            cancelIconImageView?.visibility = if (value) View.VISIBLE else View.GONE
        }

    override var contentContainerAlpha: Float
        get() = recyclerView?.alpha ?: 0f
        set(value) {
            recyclerView?.alpha = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableView.addStateListener(listener = transitionListener)
        transitionListener.onExpandableStateChange(expandableView.currentExpandableState)
        expandableView.expandedInteractionView = videoPlayerView

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter
        }

        presenter.loadVideo()
        presenter.loadExtras()
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}