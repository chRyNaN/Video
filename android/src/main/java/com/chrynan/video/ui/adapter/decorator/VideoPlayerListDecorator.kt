package com.chrynan.video.ui.adapter.decorator

import android.content.Context
import com.chrynan.aaaah.AdapterViewTypes
import javax.inject.Inject

class VideoPlayerListDecorator @Inject constructor(context: Context) :
    HorizontalDividerListDecorator(context) {

    override fun shouldDrawTopDividerForViewType(viewType: Int?): Boolean =
        (viewType == AdapterViewTypes.VIDEO_INFO_CHANNEL_ADAPTER) or
                (viewType == AdapterViewTypes.VIDEO_INFO_DESCRIPTION_ADAPTER) or
                (viewType == AdapterViewTypes.SECTION_HEADER_ADAPTER)
}