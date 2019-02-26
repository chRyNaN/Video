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

class VideoInfoAdapter : AnotherAdapter<VideoInfoViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType) =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_info, parent, false)

    override fun onBindItem(view: View, item: VideoInfoViewModel) {

    }
}