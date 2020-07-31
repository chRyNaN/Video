package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.SearchQualifier
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.channel.ChannelListItemAdapter
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class SearchFragmentModule {

    @Module
    companion object {

        // Search Results Adapter

        @Provides
        @JvmStatic
        @FragmentScope
        @SearchQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @SearchQualifier.DiffProcessor
        fun provideDiffProcessor(@SearchQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @SearchQualifier.DiffDispatcher
        fun provideDiffDispatcher(@SearchQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @SearchQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @SearchQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @SearchQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @SearchQualifier.LayoutManager
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @SearchQualifier.Adapter
        @FragmentScope
        fun provideResultAdapter(
            headerAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter,
            channelListItemAdapter: ChannelListItemAdapter,
            @SearchQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                headerAdapter,
                videoRecommendationAdapter,
                channelListItemAdapter
            ),
            positionManager = LinearLayoutPositionManager(layoutManager)
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindSearchView(fragment: SearchFragment): SearchView

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: SearchFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindSearchTagItemSelectedListener(fragment: SearchFragment): SearchTagItemAdapter.SearchTagItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindChannelListItemSelectedListener(fragment: SearchFragment): ChannelListItemAdapter.ChannelListItemSelectedListener

    @Binds
    @FragmentScope
    @SearchQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@SearchQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}