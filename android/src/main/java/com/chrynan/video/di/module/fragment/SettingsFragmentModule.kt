package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.SettingsQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.SettingsListDecorator
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.view.SettingsView
import com.chrynan.video.viewmodel.AdapterItem
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class SettingsFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.Decorator
        fun provideDecorator(context: Context) = SettingsListDecorator(context)

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.DiffCalculator
        fun provideDiffCalculator() = DiffUtilCalculator<AdapterItem>()

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.DiffProcessor
        fun provideDiffProcessor(@SettingsQualifier.DiffCalculator calculator: DiffUtilCalculator<AdapterItem>): DiffProcessor<AdapterItem> =
            AndroidDiffProcessor(calculator)

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.DiffDispatcher
        fun provideDiffDispatcher(@SettingsQualifier.ItemListUpdater listener: ItemListUpdater<AdapterItem>): DiffDispatcher<AdapterItem> =
            AndroidDiffDispatcher(listener)

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.AdapterItemHandler
        fun provideAdapterItemHandler(
            @SettingsQualifier.DiffProcessor diffProcessor: DiffProcessor<AdapterItem>,
            @SettingsQualifier.DiffDispatcher diffDispatcher: DiffDispatcher<AdapterItem>,
            coroutineDispatchers: CoroutineDispatchers
        ): AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )

        @Provides
        @JvmStatic
        @FragmentScope
        @SettingsQualifier.LayoutManager
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @SettingsQualifier.Adapter
        fun provideAdapter(
            sectionHeaderAdapter: SectionHeaderAdapter,
            @SettingsQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                sectionHeaderAdapter
            ),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindSettingsView(fragment: SettingsFragment): SettingsView

    @Binds
    @FragmentScope
    @SettingsQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@SettingsQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>
}