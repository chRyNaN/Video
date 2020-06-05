package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.ServiceProviderListQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.ServiceProviderListNavigator
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.provider.ServiceProviderListItemAdapter
import com.chrynan.video.ui.fragment.ServiceProviderListFragment
import com.chrynan.video.ui.view.ServiceProviderListView
import com.chrynan.video.utils.ActivityContext
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ServiceProviderListFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        @ServiceProviderListQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @ServiceProviderListQualifier.DiffProcessor
        fun provideDiffProcessor(@ServiceProviderListQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @ServiceProviderListQualifier.DiffDispatcher
        fun provideDiffDispatcher(@ServiceProviderListQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @ServiceProviderListQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @ServiceProviderListQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @ServiceProviderListQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @ServiceProviderListQualifier.LayoutManager
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @ServiceProviderListQualifier.Adapter
        fun provideAdapter(
            serviceProviderListItemAdapter: ServiceProviderListItemAdapter,
            @ServiceProviderListQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                serviceProviderListItemAdapter
            ),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListView(fragment: ServiceProviderListFragment): ServiceProviderListView

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListNavigator(fragment: ServiceProviderListFragment): ServiceProviderListNavigator

    @Binds
    @FragmentScope
    @ServiceProviderListQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@ServiceProviderListQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}