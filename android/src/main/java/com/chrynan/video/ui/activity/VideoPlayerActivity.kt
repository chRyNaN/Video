package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.R
import com.chrynan.video.model.VideoLoadType
import com.chrynan.video.parcel.model.getVideoInfo
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.ui.fragment.VideoPlayerFragment

class VideoPlayerActivity : BaseActivity() {

    companion object {

        private const val KEY_VIDEO_INFO = "com.chrynan.video.keyVideoInfo"
        private const val KEY_VIDEO_LOAD_TYPE = "com.chrynan.video.keyVideoLoadType"

        fun newIntent(context: Context, videoInfo: VideoInfo) =
            Intent(context, VideoPlayerActivity::class.java).apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }

        fun newIntent(context: Context, videoLoadType: VideoLoadType) =
            Intent(context, VideoPlayerActivity::class.java).apply {
                putExtra(KEY_VIDEO_LOAD_TYPE, videoLoadType)
            }
    }

    private var videoInfo: VideoInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoLoadType = intent?.getParcelableExtra<VideoLoadType>(KEY_VIDEO_LOAD_TYPE)

        videoInfo = intent?.getVideoInfo(KEY_VIDEO_INFO)

        if (findViewById<View>(R.id.videoActivityPlayerFragmentContainer) != null && videoLoadType != null) {
            goToFragment(
                VideoPlayerFragment.newInstance(videoLoadType),
                R.id.videoActivityPlayerFragmentContainer
            )
        }
    }
}