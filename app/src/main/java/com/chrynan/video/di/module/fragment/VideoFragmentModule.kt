package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoInfoAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.VideoShowcaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.VideoFragment
import com.chrynan.video.ui.view.CollapsibleVideoView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoFragmentModule {

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
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(
                adapters = setOf(
                    videoInfoAdapter,
                    sectionHeaderAdapter,
                    videoRecommendationAdapter,
                    videoShowcaseAdapter
                )
            )
    }

    @Binds
    abstract fun bindCollapsibleVideoView(fragment: VideoFragment): CollapsibleVideoView

    @Binds
    abstract fun bindVideoInfoAdapterListener(fragment: VideoFragment): VideoInfoAdapter.Listener

    @Binds
    abstract fun bindVideoOptionsListener(fragment: VideoFragment): VideoOptionsListener
}