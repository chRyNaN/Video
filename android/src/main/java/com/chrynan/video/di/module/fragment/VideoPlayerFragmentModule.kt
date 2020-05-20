package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.presentation.view.CollapsibleVideoView
import com.chrynan.presentation.view.VideoPlayerView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.*
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoPlayerFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideAdapter(
            videoInfoHeaderAdapter: VideoInfoHeaderAdapter,
            videoInfoChannelAdapter: VideoInfoChannelAdapter,
            videoInfoDescriptionAdapter: VideoInfoDescriptionAdapter,
            videoInfoDetailsAdapter: VideoInfoDetailsAdapter,
            videoInfoProviderAdapter: VideoInfoProviderAdapter,
            sectionHeaderAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter,
            videoShowcaseAdapter: VideoShowcaseAdapter,
            layoutManager: LinearLayoutManager
        ) =
            RecyclerViewAdapter(
                adapters = setOf(
                    videoInfoHeaderAdapter,
                    videoInfoChannelAdapter,
                    videoInfoDescriptionAdapter,
                    videoInfoDetailsAdapter,
                    videoInfoProviderAdapter,
                    sectionHeaderAdapter,
                    videoRecommendationAdapter,
                    videoShowcaseAdapter
                ),
                layoutManager = layoutManager
            )
    }

    @Binds
    @FragmentScope
    abstract fun bindVideoPlayerView(fragment: VideoPlayerFragment): VideoPlayerView

    @Binds
    @FragmentScope
    abstract fun bindCollapsibleVideoView(fragment: VideoPlayerFragment): CollapsibleVideoView

    @Binds
    @FragmentScope
    abstract fun bindVideoOptionsListener(fragment: VideoPlayerFragment): VideoOptionsListener
}