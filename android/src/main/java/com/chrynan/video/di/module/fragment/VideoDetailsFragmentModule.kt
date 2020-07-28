package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.VideoActionQualifier
import com.chrynan.video.di.qualifier.VideoPlayerQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.player.AndroidMediaController
import com.chrynan.video.player.converter.DelegatePlayableConverter
import com.chrynan.video.presenter.VideoDetailsPresenter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.VideoPlayerListDecorator
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.ui.fragment.VideoDetailsFragment
import com.chrynan.video.ui.view.VideoDetailsView
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.viewmodel.AdapterItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class VideoDetailsFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideMediaController(
            exoPlayer: SimpleExoPlayer,
            converter: DelegatePlayableConverter
        ): com.chrynan.video.player.MediaController = AndroidMediaController(exoPlayer, converter)

        // Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @VideoPlayerQualifier.Decorator
        fun provideDecorator(@ActivityContextQualifier context: ActivityContext) =
            VideoPlayerListDecorator(context)

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
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @VideoPlayerQualifier.Adapter
        fun provideAdapter(
            videoInfoHeaderAdapter: VideoInfoHeaderAdapter,
            videoInfoChannelAdapter: VideoInfoChannelAdapter,
            videoInfoDescriptionAdapter: VideoInfoAboutAdapter,
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
        fun provideVideoDetailsPresenter(
            coroutineDispatchers: CoroutineDispatchers,
            @VideoPlayerQualifier.AdapterItemHandler adapterItemHandler: AdapterItemHandler<AdapterItem>,
            view: VideoDetailsView
        ) = VideoDetailsPresenter(
            dispatchers = coroutineDispatchers,
            adapterHandler = adapterItemHandler,
            view = view
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
        fun provideActionAdapterLayoutManager(@ActivityContextQualifier context: ActivityContext) =
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
    abstract fun bindVideoDetailsView(fragment: VideoDetailsFragment): VideoDetailsView

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: VideoDetailsFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoShowcaseItemSelectedListener(fragment: VideoDetailsFragment): VideoShowcaseAdapter.VideoShowcaseItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoActionSelectedListener(fragment: VideoDetailsFragment): VideoInfoActionAdapter.VideoActionSelectedListener

    @Binds
    @FragmentScope
    @VideoPlayerQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@VideoPlayerQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>

    @Binds
    @FragmentScope
    @VideoActionQualifier.ItemListUpdater
    abstract fun bindActionAdapterUpdateListener(@VideoActionQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}