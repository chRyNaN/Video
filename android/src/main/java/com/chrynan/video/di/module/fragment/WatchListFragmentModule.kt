package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.ui.view.WatchListView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import com.chrynan.video.ui.fragment.WatchListFragment
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class WatchListFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideAdapter(
            watchListAdapter: WatchListItemAdapter,
            layoutManager: LinearLayoutManager
        ) =
            RecyclerViewAdapter(
                adapters = setOf(
                    watchListAdapter
                ),
                layoutManager = layoutManager
            )
    }

    @Binds
    @FragmentScope
    abstract fun bindWatchListView(fragment: WatchListFragment): WatchListView

    @Binds
    @FragmentScope
    abstract fun bindWatchListItemSelectedListener(fragment: WatchListFragment): WatchListItemAdapter.WatchListItemSelectedListener
}