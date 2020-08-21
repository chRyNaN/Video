package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.common.model.api.VideoInfo
import com.chrynan.video.presentation.presenter.VideoPlayerPresenter
import com.chrynan.video.R
import com.chrynan.video.model.VideoLoadType
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.presentation.navigator.VideoPlayerScreen
import com.chrynan.video.presentation.state.VideoPlayerChange
import com.chrynan.video.presentation.state.VideoPlayerIntent
import com.chrynan.video.presentation.state.VideoPlayerState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoPlayerFragment :
    BaseFragment<VideoPlayerIntent, VideoPlayerState, VideoPlayerChange, VideoPlayerScreen>() {

    companion object {

        private const val KEY_VIDEO_INFO = "com.chrynan.video.keyVideoInfo"
        private const val KEY_VIDEO_LOAD_TYPE = "com.chrynan.video.keyVideoLoadType"

        fun newInstance(videoInfo: VideoInfo) = VideoPlayerFragment().apply {
            arguments = Bundle().apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }
        }

        fun newInstance(videoLoadType: VideoLoadType) = VideoPlayerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_VIDEO_LOAD_TYPE, videoLoadType)
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

        val videoLoadType = arguments?.getParcelable<VideoLoadType>(KEY_VIDEO_LOAD_TYPE)
    }

    override fun intents(): Flow<VideoPlayerIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: VideoPlayerState) {
        super.render(state)

        TODO("Not yet implemented")
    }

    override fun goTo(screen: VideoPlayerScreen) {
        TODO("Not yet implemented")
    }
}