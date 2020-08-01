package com.chrynan.video.ui.fragment

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.presenter.VideoPlayerPresenter
import com.chrynan.video.R
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.player.exception.NullPlayerViewException
import com.chrynan.video.ui.view.VideoPlayerView
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoPlayerFragment : BaseFragment(),
    VideoPlayerView {

    companion object {

        private const val KEY_VIDEO_INFO = "com.chrynan.video.keyVideoInfo"
        private const val KEY_VIDEO_CONTENT_URI = "com.chrynan.video.keyVideoContentUri"

        fun newInstance(videoInfo: VideoInfo) = VideoPlayerFragment().apply {
            arguments = Bundle().apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }
        }

        fun newInstance(videoUri: Uri) = VideoPlayerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_VIDEO_CONTENT_URI, videoUri)
            }
        }
    }

    @Inject
    override lateinit var presenter: VideoPlayerPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoUri = arguments?.getParcelable<Uri>(KEY_VIDEO_CONTENT_URI)

        videoPlayerWidget

        presenter.loadVideo(videoUri)
    }

    override val widget: PlayerView
        get() = videoPlayerWidget?.widget ?: throw NullPlayerViewException()

    override fun setPreviewImage(drawable: Drawable?) {
        videoPlayerWidget?.setPreviewImage(drawable)
    }

    override fun setPreviewImage(uri: String?) {
        videoPlayerWidget?.setPreviewImage(uri)
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        videoPlayerWidget?.togglePreviewImageVisibility(isVisible)
    }
}