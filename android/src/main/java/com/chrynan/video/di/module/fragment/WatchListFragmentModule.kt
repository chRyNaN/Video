package com.chrynan.video.di.module.fragment

import com.chrynan.presentation.view.WatchListView
import com.chrynan.video.ui.adapter.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.WatchListItemAdapter
import com.chrynan.video.ui.fragment.WatchListFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class WatchListFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAdapter(watchListAdapter: WatchListItemAdapter) =
            RecyclerViewAdapter(adapters = setOf(watchListAdapter))
    }

    @Binds
    abstract fun bindWatchListView(fragment: WatchListFragment): WatchListView

    @Binds
    abstract fun bindWatchListItemSelectedListener(fragment: WatchListFragment): WatchListItemAdapter.WatchListItemSelectedListener
}