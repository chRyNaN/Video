package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.video.R
import com.chrynan.video.parcel.model.getVideoInfo
import com.chrynan.video.parcel.model.putVideoInfo
import com.chrynan.video.ui.fragment.VideoPlayerFragment

class VideoPlayerActivity : BaseActivity() {

    companion object {

        private const val KEY_VIDEO_INFO = "com.chrynan.video.keyVideoInfo"
        private const val KEY_VIDEO_CONTENT_URI = "com.chrynan.video.keyVideoContentUri"

        fun newIntent(context: Context, videoInfo: VideoInfo) =
            Intent(context, VideoPlayerActivity::class.java).apply {
                putVideoInfo(KEY_VIDEO_INFO, videoInfo)
            }

        fun newIntent(context: Context, videoUri: Uri) =
            Intent(context, VideoPlayerActivity::class.java).apply {
                putExtra(KEY_VIDEO_CONTENT_URI, videoUri)
            }
    }

    private var videoInfo: VideoInfo? = null
    private var videoContentUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoInfo = intent?.getVideoInfo(KEY_VIDEO_INFO)
        videoContentUri = intent?.getParcelableExtra(KEY_VIDEO_CONTENT_URI)

        videoInfo?.let {
            goToFragment(
                VideoPlayerFragment.newInstance(it),
                R.id.videoPlayerFragmentContainer
            )
        }
    }
}