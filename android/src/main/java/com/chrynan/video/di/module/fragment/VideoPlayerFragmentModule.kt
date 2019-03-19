package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.presentation.view.CollapsibleVideoView
import com.chrynan.presentation.view.VideoPlayerView
import com.chrynan.presentation.viewmodel.UniqueListItem
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoInfoAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.VideoShowcaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoPlayerFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAdapter(
            videoInfoAdapter: VideoInfoAdapter,
            sectionHeaderAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter,
            videoShowcaseAdapter: VideoShowcaseAdapter
        ) =
            ManagerRecyclerViewAdapter<UniqueListItem>(
                adapters = setOf(
                    videoInfoAdapter,
                    sectionHeaderAdapter,
                    videoRecommendationAdapter,
                    videoShowcaseAdapter
                )
            )
    }

    @Binds
    abstract fun bindVideoPlayerView(fragment: VideoPlayerFragment): VideoPlayerView

    @Binds
    abstract fun bindCollapsibleVideoView(fragment: VideoPlayerFragment): CollapsibleVideoView

    @Binds
    abstract fun bindVideoInfoAdapterListener(fragment: VideoPlayerFragment): VideoInfoAdapter.Listener

    @Binds
    abstract fun bindVideoOptionsListener(fragment: VideoPlayerFragment): VideoOptionsListener
}