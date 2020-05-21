package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.FilterItemAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
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

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @Named("FilterItemAdapter")
        @FragmentScope
        fun provideFilterAdapter(
            filterItemAdapter: FilterItemAdapter,
            layoutManager: LinearLayoutManager
        ) =
            RecyclerViewAdapter(
                adapters = setOf(
                    filterItemAdapter
                ),
                layoutManager = layoutManager
            )

        @JvmStatic
        @Provides
        @Named("ResultAdapter")
        @FragmentScope
        fun provideResultAdapter(
            headerAdapter: SectionHeaderAdapter,
            videoRecommendationAdapter: VideoRecommendationAdapter,
            layoutManager: LinearLayoutManager
        ) = RecyclerViewAdapter(
            adapters = setOf(
                headerAdapter,
                videoRecommendationAdapter
            ),
            layoutManager = layoutManager
        )
    }

    @Binds
    @FragmentScope
    abstract fun bindSearchView(fragment: SearchFragment): SearchView

    @Binds
    @FragmentScope
    abstract fun bindFilterItemCheckedChangeListener(fragment: SearchFragment): FilterItemAdapter.FilterItemCheckedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoInfoAdapterListener(fragment: SearchFragment): VideoOptionsListener
}