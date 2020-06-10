package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_recommendation.view.*
import javax.inject.Inject

@Adapter
class VideoRecommendationAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: VideoRecommendationItemSelectedListener
) : BaseAdapter<VideoRecommendationViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoRecommendationAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoRecommendationViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_video_recommendation, parent, false)

    override fun View.onBindItem(item: VideoRecommendationViewModel, position: Int) {
        adapterVideoRecommendationTitleTextView?.text = item.title
        adapterVideoRecommendationChannelNameTextView?.text = item.channelName
        adapterVideoRecommendationDetailsTextView?.text = item.detailText
        adapterVideoRecommendationVideoLengthTextView?.text = item.videoLength

        adapterVideoRecommendationOverflowOptionsImageView?.setOnClickListener {
            listener.onVideoRecommendationOptionsSelected(item = item)
        }

        setOnClickListener { listener.onVideoRecommendationItemSelected(item = item) }
    }

    interface VideoRecommendationItemSelectedListener {

        fun onVideoRecommendationItemSelected(item: VideoRecommendationViewModel)

        fun onVideoRecommendationOptionsSelected(item: VideoRecommendationViewModel)
    }
}