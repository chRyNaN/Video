package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.model.api.VideoAction
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.presentation.viewmodel.VideoInfoActionViewModel
import kotlinx.android.synthetic.main.adapter_video_info_action.view.*
import javax.inject.Inject

@Adapter
class VideoInfoActionAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: VideoActionSelectedListener
) : BaseAdapter<VideoInfoActionViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoActionAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoActionViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_action, parent, false)

    override fun View.onBindItem(item: VideoInfoActionViewModel, position: Int) {
        val action = item.action

        adapterVideoInfoActionButton?.load(item.icon)
        //adapterVideoInfoActionButton?.isActivated = action is SelectableAction && action.isSelected
        adapterVideoInfoActionButton?.setOnClickListener {
            listener.onVideoActionSelected(action)
        }
    }

    interface VideoActionSelectedListener {

        fun onVideoActionSelected(action: VideoAction)
    }
}