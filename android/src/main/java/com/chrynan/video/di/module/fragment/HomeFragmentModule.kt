package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.HomeQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.HomeListDecorator
import com.chrynan.video.ui.adapter.video.VideoShowcaseAdapter
import com.chrynan.video.ui.fragment.HomeFragment
import com.chrynan.video.ui.view.HomeView
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class HomeFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.Decorator
        fun provideDecorator(@ActivityContextQualifier context: ActivityContext) =
            HomeListDecorator(context)

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.DiffProcessor
        fun provideDiffProcessor(@HomeQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.DiffDispatcher
        fun provideDiffDispatcher(@HomeQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @HomeQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @HomeQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @HomeQualifier.LayoutManager
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @HomeQualifier.Adapter
        fun provideAdapter(
            sectionHeaderAdapter: SectionHeaderAdapter,
            videoShowcaseAdapter: VideoShowcaseAdapter,
            @HomeQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                sectionHeaderAdapter,
                videoShowcaseAdapter
            ),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindHomeView(fragment: HomeFragment): HomeView

    @Binds
    @FragmentScope
    abstract fun bindVideoShowcaseItemSelectedListener(fragment: HomeFragment): VideoShowcaseAdapter.VideoShowcaseItemSelectedListener

    @Binds
    @FragmentScope
    @HomeQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@HomeQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}