package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.presentation.view.WatchListView
import com.chrynan.presentation.viewmodel.UniqueListItem
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
            ManagerRecyclerViewAdapter<UniqueListItem>(adapters = setOf(watchListAdapter))
    }

    @Binds
    abstract fun bindWatchListView(fragment: WatchListFragment): WatchListView

    @Binds
    abstract fun bindWatchListItemSelectedListener(fragment: WatchListFragment): WatchListItemAdapter.WatchListItemSelectedListener
}