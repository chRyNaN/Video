package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.model.api.VideoAction
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.presenter.VideoPlayerPresenter
import com.chrynan.video.R
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.common.model.core.UriString
import com.chrynan.video.di.qualifier.VideoPlayerQualifier
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.video.VideoInfoActionAdapter
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.ui.view.VideoPlayerView
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment(),
    VideoPlayerView,
    VideoRecommendationAdapter.VideoRecommendationItemSelectedListener,
    VideoShowcaseAdapter.VideoShowcaseItemSelectedListener,
    VideoInfoActionAdapter.VideoActionSelectedListener {

    companion object {

        private const val KEY_VIDEO_INFO = "keyVideoInfo"

        fun newInstance(videoInfo: VideoInfo) = VideoPlayerFragment().apply {
            arguments = Bundle().apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }
        }
    }

    @Inject
    override lateinit var presenter: VideoPlayerPresenter

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoRecyclerView?.adapter = adapter
        videoRecyclerView?.layoutManager = layoutManager
        videoRecyclerView?.addItemDecoration(decorator)

        presenter.loadVideo()
        presenter.loadExtras()
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

    override fun attachPlayer(player: Player) {
        videoPlayerWidget?.attachPlayer(player)
    }

    override fun detachPlayer() {
        videoPlayerWidget?.detachPlayer()
    }

    override fun showPreviewImage(previewImageUri: UriString?) {
        videoPlayerWidget?.showPreviewImage(previewImageUri)
    }

    override fun showVideo() {
        videoPlayerWidget?.showVideo()
    }

    override fun onVideoActionSelected(action: VideoAction) {

    }
}