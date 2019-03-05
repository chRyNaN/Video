package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.ui.adapter.FilterItemAdapter
import com.chrynan.video.ui.fragment.SearchFragment
import com.chrynan.video.ui.view.SearchView
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class SearchFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideFilterAdapter(filterItemAdapter: FilterItemAdapter) =
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(filterItemAdapter))
    }

    @Binds
    abstract fun bindSearchView(fragment: SearchFragment): SearchView

    @Binds
    abstract fun bindFilterItemCheckedChangeListener(fragment: SearchFragment): FilterItemAdapter.FilterItemCheckedListener
}