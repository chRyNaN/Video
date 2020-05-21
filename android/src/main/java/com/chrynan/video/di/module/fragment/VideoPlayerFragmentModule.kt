package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presenter.VideoPlayerPresenter
import com.chrynan.video.ui.view.CollapsibleVideoView
import com.chrynan.video.ui.view.VideoPlayerView
import com.chrynan.video.di.qualifier.VideoActionQualifier
import com.chrynan.video.di.qualifier.VideoPlayerQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.media.MediaPlayerView
import com.chrynan.video.ui.adapter.*
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.VideoPlayerFragment
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoPlayerFragmentModule {

    @Module
    companion object {

        // Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.Decorator
        fun provideDecorator(context: Context) = VideoPlayerListDecorator(context)

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.DiffProcessor
        fun provideDiffProcessor(@VideoPlayerQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.DiffDispatcher
        fun provideDiffDispatcher(@VideoPlayerQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @VideoPlayerQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @VideoPlayerQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.LayoutManager
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @VideoPlayerQualifier.Adapter
        fun provideAdapter(
            videoInfoHeaderAdapter: VideoInfoHeaderAdapter,
            videoInfoChannelAdapter: VideoInfoChannelAdapter,
            videoInfoDescriptionAdapter: VideoInfoDescriptionAdapter,
            videoInfoDetailsAdapter: VideoInfoDetailsAdapter,
            sectionHeaderAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter,
            videoShowcaseAdapter: VideoShowcaseAdapter,
            @VideoPlayerQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                videoInfoHeaderAdapter,
                videoInfoChannelAdapter,
                videoInfoDescriptionAdapter,
                videoInfoDetailsAdapter,
                sectionHeaderAdapter,
                videoRecommendationAdapter,
                videoShowcaseAdapter
            ),
            layoutManager = layoutManager
        )

        // Presenter

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideVideoPlayerPresenter(
            coroutineDispatchers: CoroutineDispatchers,
            @VideoPlayerQualifier.AdapterItemHandler adapterItemHandler: AdapterItemHandler<AdapterItem>
        ) = VideoPlayerPresenter(
            dispatchers = coroutineDispatchers,
            adapterHandler = adapterItemHandler
        )

        // Video Action Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoActionQualifier.DiffCalculator
        fun provideActionAdapterDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoActionQualifier.DiffProcessor
        fun provideActionAdapterDiffProcessor(@VideoActionQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoActionQualifier.DiffDispatcher
        fun provideActionAdapterDiffDispatcher(@VideoActionQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoActionQualifier.AdapterItemHandler
        fun provideActionAdapterAdapterItemHandler(
            @VideoActionQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @VideoActionQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoActionQualifier.LayoutManager
        fun provideActionAdapterLayoutManager(context: Context) =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        @JvmStatic
        @Provides
        @FragmentScope
        @VideoActionQualifier.Adapter
        fun provideVideoActionAdapter(
            videoInfoProviderAdapter: VideoInfoProviderAdapter,
            videoInfoActionAdapter: VideoInfoActionAdapter,
            @VideoActionQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(videoInfoProviderAdapter, videoInfoActionAdapter),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindVideoPlayerView(fragment: VideoPlayerFragment): VideoPlayerView

    @Binds
    @FragmentScope
    abstract fun bindMediaPlayerView(fragment: VideoPlayerFragment): MediaPlayerView

    @Binds
    @FragmentScope
    abstract fun bindCollapsibleVideoView(fragment: VideoPlayerFragment): CollapsibleVideoView

    @Binds
    @FragmentScope
    abstract fun bindVideoOptionsListener(fragment: VideoPlayerFragment): VideoOptionsListener

    @Binds
    @FragmentScope
    @VideoPlayerQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@VideoPlayerQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>

    @Binds
    @FragmentScope
    @VideoActionQualifier.ItemListUpdater
    abstract fun bindActionAdapterUpdateListener(@VideoActionQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}