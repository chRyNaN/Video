package com.chrynan.video.ui.adapter.decorator

import android.content.Context
import com.chrynan.aaaah.AdapterViewTypes
import javax.inject.Inject

class ChannelListDecorator @Inject constructor(context: Context) :
    HorizontalDividerListDecorator(context) {

    override fun shouldDrawTopDividerForViewType(viewType: Int?): Boolean =
        (viewType == AdapterViewTypes.SECTION_HEADER_ADAPTER) or
                (viewType == AdapterViewTypes.CHANNEL_VIDEO_LIST_ADAPTER)
}