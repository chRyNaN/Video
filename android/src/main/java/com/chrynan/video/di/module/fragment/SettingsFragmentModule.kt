package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.qualifier.SettingsQualifier
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.SettingsNavigator
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.SettingsListDecorator
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.adapter.settings.SettingsItemAdapter
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.view.SettingsView
import com.chrynan.video.utils.ActivityContext
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
        fun provideDecorator(@ActivityContextQualifier context: ActivityContext) =
            SettingsListDecorator(context)

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
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        @SettingsQualifier.Adapter
        fun provideAdapter(
            sectionHeaderAdapter: SectionHeaderAdapter,
            settingsItemAdapter: SettingsItemAdapter,
            @SettingsQualifier.LayoutManager layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                sectionHeaderAdapter,
                settingsItemAdapter
            ),
            positionManager = LinearLayoutPositionManager(layoutManager)
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindSettingsView(fragment: SettingsFragment): SettingsView

    @Binds
    @FragmentScope
    abstract fun bindSettingsNavigator(fragment: SettingsFragment): SettingsNavigator

    @Binds
    @FragmentScope
    @SettingsQualifier.ItemListUpdater
    abstract fun bindUpdateListener(@SettingsQualifier.Adapter adapter: RecyclerViewAdapter): ItemListUpdater<AdapterItem>

    @Binds
    @FragmentScope
    abstract fun bindSettingsItemSelectedListener(fragment: SettingsFragment): SettingsItemAdapter.SettingsItemSelectedListener
}