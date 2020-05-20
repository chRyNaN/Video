package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.VideoInfoDetailsViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_info_details.view.*
import javax.inject.Inject

@Adapter
class VideoInfoDetailsAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VideoInfoDetailsViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoDetailsAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoDetailsViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_details, parent, false)

    override fun View.onBindItem(item: VideoInfoDetailsViewModel, position: Int) {
        categoryTextView?.text = item.category
        publishedTimeTextView?.text = item.publishedDate
        tagsTextView?.text = item.tags.toString()
    }
}