package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.ChannelQualifier
import com.chrynan.video.di.qualifier.ChannelVideoListQualifier
import com.chrynan.video.ui.view.ChannelView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.channel.*
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.decorator.ChannelListDecorator
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.ChannelFragment
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ChannelFragmentModule {

    @Module
    companion object {

        // Channel Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.Decorator
        fun provideDecorator(@ActivityContextQualifier context: ActivityContext) =
            ChannelListDecorator(context)

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.DiffProcessor
        fun provideDiffProcessor(@ChannelQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.DiffDispatcher
        fun provideDiffDispatcher(@ChannelQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @ChannelQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @ChannelQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelQualifier.LayoutManager
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @ChannelQualifier.Adapter
        fun provideAdapter(
            channelHeaderAdapter: ChannelHeaderAdapter,
            channelProviderAdapter: ChannelProviderAdapter,
            channelListAdapter: ChannelVideoListAdapter,
            channelAdapter: ChannelInfoAdapter,
            headerAdapter: SectionHeaderAdapter,
            videoAdapter: VideoRecommendationAdapter,
            @ChannelQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                channelHeaderAdapter,
                channelProviderAdapter,
                channelListAdapter,
                channelAdapter,
                headerAdapter,
                videoAdapter
            ),
            layoutManager = layoutManager
        )

        // Channel Video List Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelVideoListQualifier.DiffCalculator
        fun provideActionAdapterDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelVideoListQualifier.DiffProcessor
        fun provideActionAdapterDiffProcessor(@ChannelVideoListQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelVideoListQualifier.DiffDispatcher
        fun provideActionAdapterDiffDispatcher(@ChannelVideoListQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelVideoListQualifier.AdapterItemHandler
        fun provideActionAdapterAdapterItemHandler(
            @ChannelVideoListQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @ChannelVideoListQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @ChannelVideoListQualifier.LayoutManager
        fun provideActionAdapterLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        @JvmStatic
        @Provides
        @FragmentScope
        @ChannelVideoListQualifier.Adapter
        fun provideVideoActionAdapter(
            listItemAdapter: ChannelVideoListItemAdapter,
            @ChannelVideoListQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(listItemAdapter),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindChannelView(fragment: ChannelFragment): ChannelView

    @Binds
    @FragmentScope
    abstract fun bindVideoOptionsListener(fragment: ChannelFragment): VideoOptionsListener

    @Binds
    @FragmentScope
    abstract fun bindSubscribeButtonSelectedListener(fragment: ChannelFragment): ChannelHeaderAdapter.SubscribeButtonSelectedListener

    @Binds
    @FragmentScope
    @ChannelQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@ChannelQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>

    @Binds
    @FragmentScope
    @ChannelVideoListQualifier.ItemListUpdater
    abstract fun bindActionAdapterUpdateListener(@ChannelVideoListQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}