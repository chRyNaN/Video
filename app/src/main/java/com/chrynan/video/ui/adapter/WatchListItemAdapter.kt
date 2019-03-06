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
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.model.WatchListItemViewModel
import kotlinx.android.synthetic.main.adapter_watch_list_item.view.*
import javax.inject.Inject

class WatchListItemAdapter @Inject constructor(private val listener: WatchListItemSelectedListener) :
    AnotherAdapter<WatchListItemViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is WatchListItemViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_watch_list_item, parent, false)

    override fun onBindItem(view: View, item: WatchListItemViewModel) {
        view.apply {
            titleTextView?.text = item.title
            descriptionTextView?.text = item.description
            secondaryDescriptionTextView?.text = item.secondaryDescription

            Glide.with(context)
                .load(item.videoImageUri)
                .into(videoImageView)

            watchListItemContainer?.setOnClickListener { listener.onWatchListItemSelected(videoInfo = item.videoInfo) }
        }
    }

    interface WatchListItemSelectedListener {

        fun onWatchListItemSelected(videoInfo: VideoInfo)
    }
}