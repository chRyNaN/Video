package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.presentation.view.SearchView
import com.chrynan.video.ui.adapter.FilterItemAdapter
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.fragment.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
internal abstract class SearchFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named("FilterItemAdapter")
        fun provideFilterAdapter(filterItemAdapter: FilterItemAdapter) =
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(filterItemAdapter))

        @JvmStatic
        @Provides
        @Named("ResultAdapter")
        fun provideResultAdapter(
            headerAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter
        ) =
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(headerAdapter, videoRecommendationAdapter))
    }

    @Binds
    abstract fun bindSearchView(fragment: SearchFragment): SearchView

    @Binds
    abstract fun bindFilterItemCheckedChangeListener(fragment: SearchFragment): FilterItemAdapter.FilterItemCheckedListener

    @Binds
    abstract fun bindVideoInfoAdapterListener(fragment: SearchFragment): VideoOptionsListener
}