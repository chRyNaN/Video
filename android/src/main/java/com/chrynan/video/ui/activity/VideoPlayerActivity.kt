package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.R
import com.chrynan.video.parcel.model.getVideoInfo
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.ui.fragment.VideoPlayerFragment

class VideoPlayerActivity : BaseActivity() {

    companion object {

        private const val KEY_VIDEO_INFO = "keyVideoInfo"

        fun newIntent(context: Context, videoInfo: VideoInfo) =
            Intent(context, VideoPlayerActivity::class.java).apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }
    }

    private var videoInfo: VideoInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoInfo = intent?.getVideoInfo(KEY_VIDEO_INFO)

        videoInfo?.let { goToFragment(VideoPlayerFragment.newInstance(it)) }
    }
}