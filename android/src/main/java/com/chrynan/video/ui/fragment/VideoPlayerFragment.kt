package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.model.api.VideoAction
import com.chrynan.video.presenter.VideoPlayerPresenter
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.core.UriString
import com.chrynan.video.di.qualifier.VideoPlayerQualifier
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.video.VideoInfoActionAdapter
import com.chrynan.video.ui.view.VideoPlayerView
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment(),
    VideoPlayerView,
    VideoOptionsListener,
    VideoInfoActionAdapter.VideoActionSelectedListener {

    companion object {

        fun newInstance() = VideoPlayerFragment()
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

        videoOverlayWidget?.setup()

        videoOverlayWidget?.setupAdapter(adapter, layoutManager, decorator)

        presenter.loadVideo()
        presenter.loadExtras()
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }

    override fun attachPlayer(player: Player) {
        videoOverlayWidget?.attachPlayer(player)
    }

    override fun detachPlayer() {
        videoOverlayWidget?.detachPlayer()
    }

    override fun showPreviewImage(previewImageUri: UriString?) {
        videoOverlayWidget?.showPreviewImage(previewImageUri)
    }

    override fun showVideo() {
        videoOverlayWidget?.showVideo()
    }

    override fun onVideoActionSelected(action: VideoAction) {

    }
}