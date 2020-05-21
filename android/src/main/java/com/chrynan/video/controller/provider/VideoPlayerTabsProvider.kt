package com.chrynan.video.controller.provider

import com.chrynan.video.controller.tab.VideoPlayerTabs
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import javax.inject.Inject

class VideoPlayerTabsProvider @Inject constructor() : TabFragmentProvider<VideoPlayerTabs> {

    override val rootTabCount = VideoPlayerTabs.values().size

    override fun getTabFromIndex(index: Int) =
        VideoPlayerTabs.values().firstOrNull { it.index == index }

    override fun getRootTabFragment(tab: VideoPlayerTabs) =
        when (tab) {
            VideoPlayerTabs.VIDEO -> VideoPlayerFragment.newInstance()
        }
}