package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.VideoInfoDescriptionViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_info_description.view.*
import javax.inject.Inject

@Adapter
class VideoInfoDescriptionAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VideoInfoDescriptionViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoDescriptionAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoDescriptionViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_description, parent, false)

    override fun View.onBindItem(item: VideoInfoDescriptionViewModel, position: Int) {
        descriptionTextView?.text = item.description
    }
}