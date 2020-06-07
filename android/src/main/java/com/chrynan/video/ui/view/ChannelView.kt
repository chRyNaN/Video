package com.chrynan.video.ui.view

import com.chrynan.common.model.core.UriString

interface ChannelView : View {

    fun showTitle(title: String)

    fun showBannerImage(imageUri: UriString)
}