package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.VideoAction
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.viewmodel.VideoInfoActionViewModel
import kotlinx.android.synthetic.main.adapter_video_info_action.view.*
import javax.inject.Inject

@Adapter
class VideoInfoActionAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VideoInfoActionViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoActionAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoActionViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_action, parent, false)

    override fun View.onBindItem(item: VideoInfoActionViewModel, position: Int) {
        val action = item.action

        if (action is VideoAction.LocalAction) {
            adapterVideoInfoActionButton?.setImageResource(action.icon)
        } else if (action is VideoAction.BinaryAction) {
            Glide.with(adapterVideoInfoActionButton)
                .load(action.icon)
                .into(adapterVideoInfoActionButton)
            adapterVideoInfoActionButton?.isActivated = action.isSelected
        }
    }
}