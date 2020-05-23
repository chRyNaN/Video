package com.chrynan.video.ui.adapter.decorator

import android.content.Context
import javax.inject.Inject

class SettingsListDecorator @Inject constructor(context: Context) :
    HorizontalDividerListDecorator(context) {

    override fun shouldDrawTopDividerForViewType(viewType: Int?) = false
}