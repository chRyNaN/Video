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
import com.chrynan.video.model.QueueItemViewModel
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import kotlinx.android.synthetic.main.adapter_queue_item.view.*
import javax.inject.Inject

class QueueItemAdapter @Inject constructor(private val listener: QueueOptionsListener) :
    AnotherAdapter<QueueItemViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is QueueItemViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_queue_item, parent, false)

    override fun onBindItem(view: View, item: QueueItemViewModel) {
        view.apply {
            titleTextView?.text = item.title

            Glide.with(context)
                .load(item.videoImageUri)
                .into(videoImageView)

            queueItemContainer?.isActivated = item.isActivated

            overflowOptionsImageView?.setOnClickListener {
                listener.queueOptionsMenuSelected(videoInfo = item.videoInfo)
            }
        }
    }
}