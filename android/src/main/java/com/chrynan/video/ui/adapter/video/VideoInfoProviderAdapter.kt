package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.VideoInfoProviderViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_info_provider.view.*
import javax.inject.Inject

@Adapter
class VideoInfoProviderAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VideoInfoProviderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoProviderAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoProviderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_provider, parent, false)

    override fun View.onBindItem(item: VideoInfoProviderViewModel, position: Int) {
        adapterVideoInfoProviderChip?.text = item.providerServiceName
    }
}