package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.VideoInfoDetailsViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.widget.ChipBackgroundColor
import com.chrynan.video.ui.widget.ChipStyle
import com.chrynan.video.ui.widget.chipOf
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
        adapterVideoInfoCategoryTextView?.text = item.category

        adapterVideoInfoDetailsTagsChipGroup?.let { group ->
            group.removeAllViews()

            item.tags.forEach {
                val chip = chipOf(
                    parent = group,
                    style = ChipStyle.ACTION,
                    backgroundColor = ChipBackgroundColor.ACCENT_ONE,
                    text = it
                )

                group.addView(chip)
            }
        }
    }
}