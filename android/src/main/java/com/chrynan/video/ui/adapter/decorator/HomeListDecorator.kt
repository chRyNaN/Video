package com.chrynan.video.ui.adapter.decorator

import android.content.Context
import com.chrynan.aaaah.AdapterViewTypes
import javax.inject.Inject

class HomeListDecorator @Inject constructor(context: Context) :
    HorizontalDividerListDecorator(context) {

    override fun shouldDrawTopDividerForViewType(viewType: Int?) = false
}