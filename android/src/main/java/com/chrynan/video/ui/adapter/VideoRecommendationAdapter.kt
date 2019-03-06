package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.R
import com.chrynan.video.model.VideoRecommendationViewModel
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import kotlinx.android.synthetic.main.adapter_video_recommendation.view.*
import com.chrynan.video.utils.Inject

class VideoRecommendationAdapter @Inject constructor(private val listener: VideoOptionsListener) :
    AnotherAdapter<VideoRecommendationViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoRecommendationViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_recommendation, parent, false)

    override fun onBindItem(view: View, item: VideoRecommendationViewModel) {
        view.apply {
            titleTextView?.text = item.title
            channelNameTextView?.text = item.channelName
            detailsTextView?.text = item.detailText
            videoLengthTextView?.text = item.videoLength

            videoRecommendationBackgroundView?.setOnClickListener {}

            overflowOptionsImageView?.setOnClickListener {
                listener.videoOptionsMenuSelected(videoInfo = item.videoInfo)
            }
        }
    }
}