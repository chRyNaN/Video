package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.ui.view.VideoView
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : BaseFragment(),
    VideoView {

    override val containerWidth: Int
        get() = containerView?.measuredWidth ?: 0

    override var videoHeight: Int
        get() = videoImageView?.measuredHeight ?: 0
        set(value) {
            videoImageView?.layoutParams?.height = value
        }

    override var videoWidth: Int
        get() = videoImageView?.measuredWidth ?: 0
        set(value) {
            videoImageView?.layoutParams?.width = value
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_video, container, false)
}