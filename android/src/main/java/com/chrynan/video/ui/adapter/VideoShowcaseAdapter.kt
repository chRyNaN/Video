package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.R
import com.chrynan.video.model.VideoShowcaseViewModel
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import kotlinx.android.synthetic.main.adapter_video_showcase.view.*
import com.chrynan.video.utils.Inject

class VideoShowcaseAdapter @Inject constructor(private val listener: VideoOptionsListener) :
    AnotherAdapter<VideoShowcaseViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoShowcaseViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_showcase, parent, false)

    override fun onBindItem(view: View, item: VideoShowcaseViewModel) {
        view.apply {
            titleTextView?.text = item.title
            detailsTextView?.text = item.details
            providerTextView?.text = item.provider
            videoLengthTextView?.text = item.videoLength

            Glide.with(context)
                .load(item.videoImageUrl)
                .into(videoShowcaseImageView)

            Glide.with(context)
                .load(item.channelImageUrl)
                .into(channelImageView)

            videoShowcaseBackgroundView?.setOnClickListener {}

            overflowOptionsImageView?.setOnClickListener {
                listener.videoOptionsMenuSelected(videoInfo = item.videoInfo)
            }
        }
    }
}