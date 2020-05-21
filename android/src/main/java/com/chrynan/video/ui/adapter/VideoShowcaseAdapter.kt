package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import kotlinx.android.synthetic.main.adapter_video_showcase.view.*
import javax.inject.Inject

class VideoShowcaseAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: VideoOptionsListener
) : BaseAdapter<VideoShowcaseViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoShowcaseViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_showcase, parent, false)

    override fun View.onBindItem(item: VideoShowcaseViewModel, position: Int) {
        adapterVideoShowcaseTitleTextView?.text = item.title
        adapterVideoShowcaseDetailsTextView?.text = item.details
        adapterVideoShowcaseProviderTextView?.text = item.provider
        adapterVideoShowcaseVideoLengthTextView?.text = item.videoLength

        Glide.with(context)
            .load(item.videoImageUrl)
            .into(adapterVideoShowcaseImageView)

        Glide.with(context)
            .load(item.channelImageUrl)
            .into(adapterVideoShowcaseChannelImageView)

        adapterVideoShowcaseBackgroundView?.setOnClickListener {}

        adapterVideoShowcaseOverflowOptionsImageView?.setOnClickListener {
            listener.videoOptionsMenuSelected(videoInfo = item.videoInfo)
        }
    }
}