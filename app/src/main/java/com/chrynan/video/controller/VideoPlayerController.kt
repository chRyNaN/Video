package com.chrynan.video.controller

import androidx.annotation.IdRes
import com.chrynan.video.controller.provider.VideoPlayerTabsProvider
import com.chrynan.video.controller.tab.VideoPlayerTabs
import com.chrynan.video.ui.activity.MainActivity
import javax.inject.Inject

class VideoPlayerController @Inject constructor(
    activity: MainActivity,
    @IdRes containerId: Int,
    tabProvider: VideoPlayerTabsProvider
) : BaseActivityController<VideoPlayerTabs>(
    activity = activity,
    containerId = containerId,
    tabProvider = tabProvider
), Controller<VideoPlayerTabs>