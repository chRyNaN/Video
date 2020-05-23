package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.viewmodel.VideoShowcaseViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import kotlinx.android.synthetic.main.adapter_video_showcase.view.*
import javax.inject.Inject

@Adapter
class VideoShowcaseAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: VideoOptionsListener
) : BaseAdapter<VideoShowcaseViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoShowcaseAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoShowcaseViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_showcase, parent, false)

    override fun View.onBindItem(item: VideoShowcaseViewModel, position: Int) {
        adapterVideoShowcaseTitleTextView?.text = item.title
        adapterVideoShowcaseDetailsTextView?.text = item.details
        adapterVideoShowcaseProviderTextView?.text = item.provider
        adapterVideoShowcaseVideoLengthTextView?.text = item.videoLength

        adapterVideoShowcaseImageView?.load(item.videoImageUrl)

        adapterVideoShowcaseChannelImageView?.load(item.channelImageUrl)

        adapterVideoShowcaseBackgroundView?.setOnClickListener {}

        adapterVideoShowcaseOverflowOptionsImageView?.setOnClickListener {
            listener.videoOptionsMenuSelected(videoInfo = item.videoInfo)
        }
    }
}