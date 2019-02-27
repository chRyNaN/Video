package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.R
import com.chrynan.video.model.VideoInfoViewModel
import kotlinx.android.synthetic.main.adapter_video_info.view.*

class VideoInfoAdapter : AnotherAdapter<VideoInfoViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType) =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_info, parent, false)

    override fun onBindItem(view: View, item: VideoInfoViewModel) {
        view.apply {
            titleTextView?.text = item.title
            viewCountTextView?.text = item.viewCount
            descriptionTextView?.text = item.description
            expandDescriptionBackgroundView?.setOnClickListener {
                descriptionGroup?.visibility =
                        if (descriptionGroup?.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }
    }
}