package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.viewmodel.VideoInfoHeaderViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_video_info_header.view.*
import javax.inject.Inject

class VideoInfoHeaderAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers
) : BaseAdapter<VideoInfoHeaderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoHeaderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_video_info_header, parent, false)

    override fun View.onBindItem(item: VideoInfoHeaderViewModel, position: Int) {
        adapterVideoInfoHeaderTitleTextView?.text = item.title
        adapterVideoInfoHeaderViewCountTextView?.text = item.viewCount
        adapterVideoInfoHeaderLikeButton?.text = item.likeButtonText
        adapterVideoInfoHeaderLikeButton?.isActivated = item.isLiked
        adapterVideoInfoHeaderDislikeButton?.text = item.dislikeButtonText
        adapterVideoInfoHeaderDislikeButton?.isActivated = item.isDisliked
        adapterVideoInfoHeaderShareButton?.text = item.shareButtonText
    }
}