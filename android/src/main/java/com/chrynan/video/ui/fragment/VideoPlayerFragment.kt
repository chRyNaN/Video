package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.presenter.VideoPlayerPresenter
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.common.model.VideoInfo
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment(),
    VideoOptionsListener {

    companion object {

        fun newInstance() = VideoPlayerFragment()
    }

    @Inject
    override lateinit var presenter: VideoPlayerPresenter

    private val videoOptionsMenuBottomSheet by lazy {
        MenuBottomSheetDialogFragment.newInstance(
            menuResId = R.menu.menu_video_options
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadVideo()
        presenter.loadExtras()
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}