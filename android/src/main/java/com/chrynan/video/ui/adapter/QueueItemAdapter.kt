package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.QueueItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import kotlinx.android.synthetic.main.adapter_queue_item.view.*
import javax.inject.Inject

@Adapter
class QueueItemAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: QueueOptionsListener
) : BaseAdapter<QueueItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(QueueItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is QueueItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_queue_item, parent, false)

    override fun View.onBindItem(item: QueueItemViewModel, position: Int) {
        adapterQueueTitleTextView?.text = item.title

        adapterQueueVideoImageView?.load(item.videoImageUri)

        queueItemContainer?.isActivated = item.isActivated

        adapterQueueOverflowOptionsImageView?.setOnClickListener {
            listener.queueOptionsMenuSelected(videoInfo = item.videoInfo)
        }
    }
}