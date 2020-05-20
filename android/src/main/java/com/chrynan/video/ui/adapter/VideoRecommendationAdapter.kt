package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import kotlinx.android.synthetic.main.adapter_video_recommendation.view.*
import javax.inject.Inject

class VideoRecommendationAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: VideoOptionsListener
) : BaseAdapter<VideoRecommendationViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoRecommendationViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_recommendation, parent, false)

    override fun View.onBindItem(item: VideoRecommendationViewModel, position: Int) {
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